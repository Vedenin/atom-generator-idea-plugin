package com.github.vedenin.idea.plugins.atom.gui;

import com.github.vedenin.idea.plugins.atom.gui.helper.GuiHelper;
import com.github.vedenin.idea.plugins.atom.psi.PsiHelper;
import com.intellij.CommonBundle;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiDirectory;
import com.intellij.util.IncorrectOperationException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SelectDirectory implements Runnable {

    private final CreateBuilderDialog createBuilderDialog;
    private final PsiHelper psiHelper;
    private final GuiHelper guiHelper;
    private final Project project;
    private final Module module;
    private final String packageName;
    private final String className;


    @Override
    public void run() {
        String errorString = null;
        try {
            PsiDirectory targetDirectory = psiHelper.getDirectoryFromModuleAndPackageName(module, packageName);
            if (targetDirectory != null) {
                createBuilderDialog.setTargetDirectory(targetDirectory);
                errorString = psiHelper.checkIfClassCanBeCreated(targetDirectory, className);
            }
        } catch (IncorrectOperationException e) {
            errorString = e.getMessage();
        }
        if (errorString != null) {
            guiHelper.showMessageDialog(project, errorString, CommonBundle.getErrorTitle(), Messages.getErrorIcon());
        }
    }
}
