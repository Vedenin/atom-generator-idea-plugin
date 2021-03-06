package com.github.vedenin.idea.plugins.atom.factory;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiManager;

public class PsiManagerFactory {

    public PsiManager getPsiManager(Project project) {
        return PsiManager.getInstance(project);
    }
}
