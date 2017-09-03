package com.github.vedenin.idea.plugins.atom.gui;

import com.github.vedenin.idea.plugins.atom.gui.helper.GuiHelper;
import com.github.vedenin.idea.plugins.atom.psi.PsiHelper;
import com.intellij.CommonBundle;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.psi.PsiDirectory;
import com.intellij.util.IncorrectOperationException;

public class SelectDirectory implements Runnable {

    private CreateBuilderDialog createBuilderDialog;
    private PsiHelper psiHelper;
    private GuiHelper guiHelper;
    private Project project;
    private Module module;
    private String packageName;
    private String className;

    public SelectDirectory(CreateBuilderDialog createBuilderDialog, PsiHelper psiHelper, GuiHelper guiHelper, Project project, Module module, String packageName, String className) {
        this.createBuilderDialog = createBuilderDialog;
        this.psiHelper = psiHelper;
        this.guiHelper = guiHelper;
        this.project = project;
        this.module = module;
        this.packageName = packageName;
        this.className = className;
    }

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
