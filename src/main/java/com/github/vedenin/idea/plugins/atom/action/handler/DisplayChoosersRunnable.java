package com.github.vedenin.idea.plugins.atom.action.handler;

import com.github.vedenin.idea.plugins.atom.factory.CreateBuilderDialogFactory;
import com.github.vedenin.idea.plugins.atom.factory.MemberChooser;
import com.github.vedenin.idea.plugins.atom.factory.MemberChooserDialogFactory;
import com.github.vedenin.idea.plugins.atom.factory.PsiFieldsForBuilderFactory;
import com.github.vedenin.idea.plugins.atom.gui.CreateBuilderDialog;
import com.github.vedenin.idea.plugins.atom.psi.PsiFieldSelector;
import com.github.vedenin.idea.plugins.atom.psi.PsiHelper;
import com.github.vedenin.idea.plugins.atom.psi.model.PsiFieldsForBuilder;
import com.github.vedenin.idea.plugins.atom.writer.BuilderContext;
import com.github.vedenin.idea.plugins.atom.writer.BuilderWriter;
import com.intellij.codeInsight.generation.PsiElementClassMember;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiPackage;

import java.util.List;

public class DisplayChoosersRunnable implements Runnable {

    private PsiClass psiClassFromEditor;
    private Project project;
    private Editor editor;
    private PsiHelper psiHelper;
    private CreateBuilderDialogFactory createBuilderDialogFactory;
    private PsiFieldSelector psiFieldSelector;
    private MemberChooserDialogFactory memberChooserDialogFactory;
    private BuilderWriter builderWriter;
    private PsiFieldsForBuilderFactory psiFieldsForBuilderFactory;

    public DisplayChoosersRunnable(PsiHelper psiHelper, CreateBuilderDialogFactory createBuilderDialogFactory,
                                   PsiFieldSelector psiFieldSelector, MemberChooserDialogFactory memberChooserDialogFactory,
                                   BuilderWriter builderWriter, PsiFieldsForBuilderFactory psiFieldsForBuilderFactory) {
        this.psiHelper = psiHelper;
        this.createBuilderDialogFactory = createBuilderDialogFactory;
        this.psiFieldSelector = psiFieldSelector;
        this.memberChooserDialogFactory = memberChooserDialogFactory;
        this.builderWriter = builderWriter;
        this.psiFieldsForBuilderFactory = psiFieldsForBuilderFactory;
    }

    @Override
    public void run() {
        CreateBuilderDialog createBuilderDialog = showDialog();
        if (createBuilderDialog.isOK()) {
            PsiDirectory targetDirectory = createBuilderDialog.getTargetDirectory();
            String className = createBuilderDialog.getClassName();
            String methodPrefix = createBuilderDialog.getMethodPrefix();
            boolean innerBuilder = createBuilderDialog.isInnerBuilder();
            boolean useSingleField = createBuilderDialog.useSingleField();
            boolean hasButMethod = createBuilderDialog.hasButMethod();
            List<PsiElementClassMember> fieldsToDisplay = getFieldsToIncludeInBuilder(psiClassFromEditor, innerBuilder, useSingleField, hasButMethod);
            MemberChooser<PsiElementClassMember> memberChooserDialog = memberChooserDialogFactory.getMemberChooserDialog(fieldsToDisplay, project);
            memberChooserDialog.show();
            writeBuilderIfNecessary(targetDirectory, className, methodPrefix, memberChooserDialog, createBuilderDialog);
        }
    }

    private void writeBuilderIfNecessary(
            PsiDirectory targetDirectory, String className, String methodPrefix, MemberChooser<PsiElementClassMember> memberChooserDialog, CreateBuilderDialog createBuilderDialog) {
        if (memberChooserDialog.isOK()) {
            List<PsiElementClassMember> selectedElements = memberChooserDialog.getSelectedElements();
            PsiFieldsForBuilder psiFieldsForBuilder = psiFieldsForBuilderFactory.createPsiFieldsForBuilder(selectedElements, psiClassFromEditor);
            BuilderContext context = new BuilderContext(
                    project, psiFieldsForBuilder, targetDirectory, className, psiClassFromEditor, methodPrefix, createBuilderDialog.isInnerBuilder(), createBuilderDialog.hasButMethod(), createBuilderDialog.useSingleField());
            builderWriter.writeBuilder(context);
        }
    }

    private CreateBuilderDialog showDialog() {
        PsiDirectory srcDir = psiHelper.getPsiFileFromEditor(editor, project).getContainingDirectory();
        PsiPackage srcPackage = psiHelper.getPackage(srcDir);
        CreateBuilderDialog dialog = createBuilderDialogFactory.createBuilderDialog(psiClassFromEditor, project, srcPackage);
        dialog.show();
        return dialog;
    }

    private List<PsiElementClassMember> getFieldsToIncludeInBuilder(PsiClass clazz, boolean innerBuilder, boolean useSingleField, boolean hasButMethod) {
        return psiFieldSelector.selectFieldsToIncludeInBuilder(clazz, innerBuilder, useSingleField, hasButMethod);
    }

    public void setPsiClassFromEditor(PsiClass psiClassFromEditor) {
        this.psiClassFromEditor = psiClassFromEditor;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void setEditor(Editor editor) {
        this.editor = editor;
    }
}
