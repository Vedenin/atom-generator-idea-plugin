package com.github.vedenin.idea.plugins.atom.writer;

import com.intellij.openapi.application.Application;
import com.github.vedenin.idea.plugins.atom.psi.BuilderPsiClassBuilder;
import com.github.vedenin.idea.plugins.atom.psi.PsiHelper;

public class BuilderWriterRunnable implements Runnable {

    private PsiHelper psiHelper = new PsiHelper();
    private BuilderPsiClassBuilder builderPsiClassBuilder;
    private BuilderContext context;

    public BuilderWriterRunnable(BuilderPsiClassBuilder builderPsiClassBuilder, BuilderContext context) {
        this.builderPsiClassBuilder = builderPsiClassBuilder;
        this.context = context;
    }

    @Override
    public void run() {
        Application application = psiHelper.getApplication();
        application.runWriteAction(new BuilderWriterComputable(builderPsiClassBuilder, context));
    }
}