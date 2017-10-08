package com.github.vedenin.idea.plugins.atom.finder;

import com.intellij.psi.PsiClass;
import lombok.RequiredArgsConstructor;

import static com.github.vedenin.idea.plugins.atom.Constants.FINDER_SEARCH_PATTERN;

@RequiredArgsConstructor
public class BuilderFinder {
    private final ClassFinder classFinder;

    public PsiClass findBuilderForClass(PsiClass psiClass) {
        PsiClass innerBuilderClass = tryFindInnerBuilder(psiClass);
        if (innerBuilderClass != null) {
            return innerBuilderClass;
        } else {
            String searchName = psiClass.getName() + FINDER_SEARCH_PATTERN;
            return findClass(psiClass, searchName);
        }
    }

    public PsiClass findClassForBuilder(PsiClass psiClass) {
        String searchName = psiClass.getName().replaceFirst(FINDER_SEARCH_PATTERN, "");
        return findClass(psiClass, searchName);
    }

    private PsiClass findClass(PsiClass psiClass, String searchName) {
        PsiClass result = null;
        if (typeIsCorrect(psiClass)) {
            result = classFinder.findClass(searchName, psiClass.getProject());
        }
        return result;
    }

    private static PsiClass tryFindInnerBuilder(PsiClass psiClass) {
        PsiClass innerBuilderClass = null;
        PsiClass[] allInnerClasses = psiClass.getAllInnerClasses();
        for (PsiClass innerClass : allInnerClasses) {
            if (innerClass.getName().contains(FINDER_SEARCH_PATTERN)) {
                innerBuilderClass = innerClass;
                break;
            }
        }
        return innerBuilderClass;
    }

    private static boolean typeIsCorrect(PsiClass psiClass) {
        return !psiClass.isAnnotationType() && !psiClass.isEnum() && !psiClass.isInterface();
    }
}
