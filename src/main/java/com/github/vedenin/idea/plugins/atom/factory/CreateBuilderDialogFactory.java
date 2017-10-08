package com.github.vedenin.idea.plugins.atom.factory;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiPackage;
import com.github.vedenin.idea.plugins.atom.gui.CreateBuilderDialog;
import com.github.vedenin.idea.plugins.atom.gui.helper.GuiHelper;
import com.github.vedenin.idea.plugins.atom.psi.PsiHelper;
import lombok.RequiredArgsConstructor;

import static com.github.vedenin.idea.plugins.atom.Constants.BUILDER_DIALOG_METHOD_PREFIX;
import static com.github.vedenin.idea.plugins.atom.Constants.BUILDER_DIALOG_NAME;
import static com.github.vedenin.idea.plugins.atom.Constants.BUILDER_SUFFIX;

@RequiredArgsConstructor
public class CreateBuilderDialogFactory {
    private final PsiHelper psiHelper;
    private final ReferenceEditorComboWithBrowseButtonFactory referenceEditorComboWithBrowseButtonFactory;
    private final GuiHelper guiHelper;

    public CreateBuilderDialog createBuilderDialog(PsiClass sourceClass, Project project, PsiPackage srcPackage) {
        return new CreateBuilderDialog(project, BUILDER_DIALOG_NAME, sourceClass, sourceClass.getName()
                + BUILDER_SUFFIX, BUILDER_DIALOG_METHOD_PREFIX, srcPackage, psiHelper, guiHelper,
                referenceEditorComboWithBrowseButtonFactory);
    }
}
