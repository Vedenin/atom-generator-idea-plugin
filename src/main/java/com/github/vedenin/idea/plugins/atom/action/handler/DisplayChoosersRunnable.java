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
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Setter
public class DisplayChoosersRunnable implements Runnable {

    private PsiClass psiClassFromEditor;
    private Project project;
    private Editor editor;
    private final PsiHelper psiHelper;
    private final CreateBuilderDialogFactory createBuilderDialogFactory;
    private final PsiFieldSelector psiFieldSelector;
    private final MemberChooserDialogFactory memberChooserDialogFactory;
    private final BuilderWriter builderWriter;
    private final PsiFieldsForBuilderFactory psiFieldsForBuilderFactory;


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

}
