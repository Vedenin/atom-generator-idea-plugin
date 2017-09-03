package com.github.vedenin.idea.plugins.atom.verifier;

import com.intellij.psi.PsiClass;

public class BuilderVerifier {

    private static final String SUFFIX = "Builder";

    public boolean isBuilder(PsiClass psiClass) {
        return psiClass.getName().endsWith(SUFFIX);
    }
}
