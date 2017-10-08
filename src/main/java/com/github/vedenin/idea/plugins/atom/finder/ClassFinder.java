package com.github.vedenin.idea.plugins.atom.finder;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import com.github.vedenin.idea.plugins.atom.psi.PsiHelper;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClassFinder {

    private final PsiHelper psiHelper;

    public PsiClass findClass(String pattern, Project project) {
        PsiClass result;
        GlobalSearchScope projectScope = GlobalSearchScope.projectScope(project);
        PsiShortNamesCache psiShortNamesCache = psiHelper.getPsiShortNamesCache(project);
        PsiClass[] classesArray = psiShortNamesCache.getClassesByName(pattern, projectScope);
        result = getPsiClass(classesArray);
        return result;
    }

    private PsiClass getPsiClass(PsiClass[] classesArray) {
        return (classesArray != null && classesArray.length != 0) ? classesArray[0] : null;
    }
}
