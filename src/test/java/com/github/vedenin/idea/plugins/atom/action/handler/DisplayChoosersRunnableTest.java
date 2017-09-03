package com.github.vedenin.idea.plugins.atom.action.handler;

import com.github.vedenin.idea.plugins.atom.factory.CreateBuilderDialogFactory;
import com.github.vedenin.idea.plugins.atom.factory.MemberChooser;
import com.github.vedenin.idea.plugins.atom.factory.PsiFieldsForBuilderFactory;
import com.github.vedenin.idea.plugins.atom.psi.PsiFieldSelector;
import com.github.vedenin.idea.plugins.atom.psi.PsiHelper;
import com.github.vedenin.idea.plugins.atom.writer.BuilderContext;
import com.github.vedenin.idea.plugins.atom.writer.BuilderWriter;
import com.intellij.codeInsight.generation.PsiElementClassMember;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiField;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiPackage;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import com.github.vedenin.idea.plugins.atom.factory.MemberChooserDialogFactory;
import com.github.vedenin.idea.plugins.atom.factory.PsiManagerFactory;
import com.github.vedenin.idea.plugins.atom.gui.CreateBuilderDialog;
import com.github.vedenin.idea.plugins.atom.psi.model.PsiFieldsForBuilder;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class DisplayChoosersRunnableTest {

    @InjectMocks private DisplayChoosersRunnable displayChoosersRunnable;
    @Mock private PsiClass psiClassFromEditor;
    @Mock private Project project;
    @Mock private Editor editor;
    @Mock private PsiHelper psiHelper;
    @Mock private PsiManagerFactory psiManagerFactory;
    @Mock private CreateBuilderDialogFactory createBuilderDialogFactory;
    @Mock private PsiFieldSelector psiFieldSelector;
    @Mock private MemberChooserDialogFactory memberChooserDialogFactory;
    @Mock private BuilderWriter builderWriter;
    @Mock private PsiFile psiFile;
    @Mock private PsiDirectory psiDirectory;
    @Mock private PsiPackage psiPackage;
    @Mock private PsiManager psiManager;
    @Mock private CreateBuilderDialog createBuilderDialog;
    @Mock private MemberChooser memberChooserDialog;
    @Mock private PsiFieldsForBuilderFactory psiFieldsForBuilderFactory;
    @Mock private PsiFieldsForBuilder psiFieldsForBuilder;

    private String className = "className";
    private PsiField[] allFields = {};
    private List<PsiElementClassMember> selectedFields = new ArrayList<>();

    @Before
    public void setUp() {
        displayChoosersRunnable.setEditor(editor);
        displayChoosersRunnable.setProject(project);
        displayChoosersRunnable.setPsiClassFromEditor(psiClassFromEditor);
        given(psiHelper.getPsiFileFromEditor(editor, project)).willReturn(psiFile);
        given(psiFile.getContainingDirectory()).willReturn(psiDirectory);
        given(psiHelper.getPackage(psiDirectory)).willReturn(psiPackage);
        given(psiManagerFactory.getPsiManager(project)).willReturn(psiManager);
        given(psiClassFromEditor.getName()).willReturn(className);
        given(psiFieldsForBuilderFactory.createPsiFieldsForBuilder(selectedFields, psiClassFromEditor)).willReturn(psiFieldsForBuilder);
        given(createBuilderDialogFactory.createBuilderDialog(psiClassFromEditor, project, psiPackage)).willReturn(createBuilderDialog);
    }

    @Test
    public void shouldDisplayCreateBuilderDialogAndDoNothingWhenOKNotSelected() {
        // given
        given(createBuilderDialog.isOK()).willReturn(false);
        // when
        displayChoosersRunnable.run();
        // then
        verify(createBuilderDialog).show();
        verifyZeroInteractions(psiFieldSelector, memberChooserDialogFactory, builderWriter);
    }


    @SuppressWarnings("unchecked")
    @Test
    public void shouldNotWriteBuilderWhenOkNotSelectedFromMemberChooserDialog() {
        // given
        given(createBuilderDialog.isOK()).willReturn(true);
        given(memberChooserDialog.isOK()).willReturn(false);
        given(createBuilderDialog.getTargetDirectory()).willReturn(psiDirectory);
        given(createBuilderDialog.getClassName()).willReturn(className);
        given(psiClassFromEditor.getAllFields()).willReturn(allFields);
        given(memberChooserDialogFactory.getMemberChooserDialog(selectedFields, project)).willReturn(memberChooserDialog);

        // when
        displayChoosersRunnable.run();

        // then
        verify(createBuilderDialog).isOK();
        verify(memberChooserDialog).isOK();
        verify(createBuilderDialog).show();
        verifyZeroInteractions(builderWriter);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void shouldDisplayCreateBuilderAndMemberChooserDialogAndWriteBuilderWhenOKSelectedFromBothWindows() {
        // given
        String methodPrefix = "with";
        boolean isInner = true;
        boolean hasButMethod = true;
        boolean useSingleField = true;
        given(createBuilderDialog.isInnerBuilder()).willReturn(isInner);
        given(createBuilderDialog.hasButMethod()).willReturn(hasButMethod);
        given(createBuilderDialog.isOK()).willReturn(true);
        given(memberChooserDialog.isOK()).willReturn(true);
        given(createBuilderDialog.getTargetDirectory()).willReturn(psiDirectory);
        given(createBuilderDialog.getClassName()).willReturn(className);
        given(createBuilderDialog.getMethodPrefix()).willReturn(methodPrefix);
        given(psiClassFromEditor.getAllFields()).willReturn(allFields);
        given(memberChooserDialogFactory.getMemberChooserDialog(selectedFields, project)).willReturn(memberChooserDialog);
        given(psiFieldSelector.selectFieldsToIncludeInBuilder(psiClassFromEditor, false, false, false)).willReturn(selectedFields);
        given(memberChooserDialog.getSelectedElements()).willReturn(selectedFields);

        // when
        displayChoosersRunnable.run();

        // then
        verify(createBuilderDialog).isOK();
        verify(memberChooserDialog).isOK();
        verify(createBuilderDialog).show();
        verify(memberChooserDialog).show();
        verify(builderWriter).writeBuilder(eq(new BuilderContext(project, psiFieldsForBuilder, psiDirectory, className, psiClassFromEditor, methodPrefix, isInner, hasButMethod, useSingleField)));
    }
}
