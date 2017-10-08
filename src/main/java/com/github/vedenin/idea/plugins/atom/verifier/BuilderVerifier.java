package com.github.vedenin.idea.plugins.atom.verifier;

import com.intellij.psi.PsiClass;

import static com.github.vedenin.idea.plugins.atom.Constants.BUILDER_SUFFIX;

public class BuilderVerifier {
    public boolean isBuilder(PsiClass psiClass) {
        return psiClass.getName().endsWith(BUILDER_SUFFIX);
    }
}
