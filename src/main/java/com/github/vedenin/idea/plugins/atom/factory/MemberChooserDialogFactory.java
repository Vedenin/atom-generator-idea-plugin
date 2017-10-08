package com.github.vedenin.idea.plugins.atom.factory;

import com.intellij.codeInsight.generation.PsiElementClassMember;
import com.intellij.openapi.project.Project;

import java.util.List;

import static com.github.vedenin.idea.plugins.atom.Constants.MEMBER_CHOOSE_DIALOG_TITLE;

public class MemberChooserDialogFactory {
    public MemberChooser<PsiElementClassMember> getMemberChooserDialog(List<PsiElementClassMember> elements, Project project) {
        PsiElementClassMember[] psiElementClassMembers = elements.toArray(new PsiElementClassMember[elements.size()]);
        MemberChooser<PsiElementClassMember> memberChooserDialog = createNewInstance(project, psiElementClassMembers);
        memberChooserDialog.setCopyJavadocVisible(false);
        memberChooserDialog.selectElements(psiElementClassMembers);
        memberChooserDialog.setTitle(MEMBER_CHOOSE_DIALOG_TITLE);
        return memberChooserDialog;
    }

    MemberChooser<PsiElementClassMember> createNewInstance(Project project, PsiElementClassMember[] psiElementClassMembers) {
        return new MemberChooser<>(psiElementClassMembers, false, true, project, false);
    }


}
