package com.github.vedenin.idea.plugins.atom.psi;

import com.intellij.codeInsight.generation.PsiElementClassMember;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiField;
import com.github.vedenin.idea.plugins.atom.factory.PsiElementClassMemberFactory;
import com.github.vedenin.idea.plugins.atom.verifier.PsiFieldVerifier;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
public class PsiFieldSelector {

    private final PsiElementClassMemberFactory psiElementClassMemberFactory;
    private final PsiFieldVerifier psiFieldVerifier;

    public List<PsiElementClassMember> selectFieldsToIncludeInBuilder(final PsiClass psiClass, final boolean innerBuilder, final boolean useSingleField, final boolean hasButMethod) {
        return stream(psiClass.getAllFields())
                .filter(psiField -> !"serialVersionUID".equals(psiField.getName()))
                .filter(psiField -> isAppropriate(psiClass, psiField, innerBuilder, useSingleField, hasButMethod))
                .map(psiElementClassMemberFactory::createPsiElementClassMember)
                .collect(toList());
    }

    private boolean isAppropriate(PsiClass psiClass, PsiField psiField, boolean innerBuilder, boolean useSingleField, boolean hasButMethod) {
        if(useSingleField && hasButMethod) {
            return psiFieldVerifier.isSetInSetterMethod(psiField, psiClass) && psiFieldVerifier.hasGetterMethod(psiField, psiClass);
        } else if(useSingleField){
            return psiFieldVerifier.isSetInSetterMethod(psiField, psiClass);
        } else if(!innerBuilder){
            return psiFieldVerifier.isSetInSetterMethod(psiField, psiClass) || psiFieldVerifier.isSetInConstructor(psiField, psiClass);
        }
        return true;
    }
}
