package com.github.vedenin.idea.plugins.atom.factory;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiPackage;
import com.github.vedenin.idea.plugins.atom.gui.CreateBuilderDialog;
import com.github.vedenin.idea.plugins.atom.gui.helper.GuiHelper;
import com.github.vedenin.idea.plugins.atom.psi.PsiHelper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateBuilderDialogFactory {

    static final String BUILDER_SUFFIX = "Builder";
    static final String METHOD_PREFIX = "with";

    private static final String DIALOG_NAME = "CreateBuilder";
    private final PsiHelper psiHelper;
    private final ReferenceEditorComboWithBrowseButtonFactory referenceEditorComboWithBrowseButtonFactory;
    private final GuiHelper guiHelper;

    public CreateBuilderDialog createBuilderDialog(PsiClass sourceClass, Project project, PsiPackage srcPackage) {
        return new CreateBuilderDialog(project, DIALOG_NAME, sourceClass, sourceClass.getName() + BUILDER_SUFFIX, METHOD_PREFIX, srcPackage, psiHelper, guiHelper,
                referenceEditorComboWithBrowseButtonFactory);
    }
}
