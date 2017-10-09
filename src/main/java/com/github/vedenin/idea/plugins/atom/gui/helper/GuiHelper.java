package com.github.vedenin.idea.plugins.atom.gui.helper;

import com.github.vedenin.atoms.openapi.MessagesAtom;
import com.intellij.codeInsight.CodeInsightUtil;
import com.intellij.openapi.fileEditor.ex.IdeDocumentHistory;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;

import javax.swing.Icon;


public class GuiHelper {

    public void includeCurrentPlaceAsChangePlace(Project project) {
        IdeDocumentHistory.getInstance(project).includeCurrentPlaceAsChangePlace();
    }

    public void positionCursor(Project project, PsiFile psiFile, PsiElement psiElement) {
        CodeInsightUtil.positionCursor(project, psiFile, psiElement);
    }
}
