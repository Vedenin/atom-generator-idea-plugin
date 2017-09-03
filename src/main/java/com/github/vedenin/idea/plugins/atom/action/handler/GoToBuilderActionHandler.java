package com.github.vedenin.idea.plugins.atom.action.handler;

import com.github.vedenin.idea.plugins.atom.finder.BuilderFinder;
import com.github.vedenin.idea.plugins.atom.gui.displayer.PopupDisplayer;
import com.github.vedenin.idea.plugins.atom.psi.PsiHelper;
import com.github.vedenin.idea.plugins.atom.verifier.BuilderVerifier;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.github.vedenin.idea.plugins.atom.factory.PopupListFactory;

import javax.swing.JList;


public class GoToBuilderActionHandler extends EditorActionHandler {

    private PsiHelper psiHelper;
    private BuilderVerifier builderVerifier;
    private BuilderFinder builderFinder;
    private PopupDisplayer popupDisplayer;
    private PopupListFactory popupListFactory;
    private DisplayChoosersRunnable displayChoosersRunnable;

    public GoToBuilderActionHandler(PsiHelper psiHelper, BuilderVerifier builderVerifier, BuilderFinder builderFinder, PopupDisplayer popupDisplayer,
                                    PopupListFactory popupListFactory, DisplayChoosersRunnable displayChoosersRunnable) {
        this.psiHelper = psiHelper;
        this.builderVerifier = builderVerifier;
        this.builderFinder = builderFinder;
        this.popupDisplayer = popupDisplayer;
        this.popupListFactory = popupListFactory;
        this.displayChoosersRunnable = displayChoosersRunnable;
    }

    @Override
    public void execute(Editor editor, DataContext dataContext) {
        Project project = (Project) dataContext.getData(DataKeys.PROJECT.getName());
        PsiClass psiClassFromEditor = psiHelper.getPsiClassFromEditor(editor, project);
        if (psiClassFromEditor != null) {
            navigateOrDisplay(editor, psiClassFromEditor, dataContext);
        }
    }

    private void navigateOrDisplay(Editor editor, PsiClass psiClassFromEditor, DataContext dataContext) {
        boolean isBuilder = builderVerifier.isBuilder(psiClassFromEditor);
        PsiClass classToGo = findClassToGo(psiClassFromEditor, isBuilder);
        if (classToGo != null) {
            psiHelper.navigateToClass(classToGo);
        } else if (!isBuilder) {
            displayPopup(editor, psiClassFromEditor, dataContext);
        }
    }

    private void displayPopup(Editor editor, PsiClass psiClassFromEditor, DataContext dataContext) {
        JList popupList = popupListFactory.getPopupList();
        Project project = (Project) dataContext.getData(DataKeys.PROJECT.getName());
        displayChoosersRunnable.setEditor(editor);
        displayChoosersRunnable.setProject(project);
        displayChoosersRunnable.setPsiClassFromEditor(psiClassFromEditor);
        popupDisplayer.displayPopupChooser(editor, popupList, displayChoosersRunnable);
    }

    private PsiClass findClassToGo(PsiClass psiClassFromEditor, boolean isBuilder) {
        if (isBuilder) {
            return builderFinder.findClassForBuilder(psiClassFromEditor);
        }
        return builderFinder.findBuilderForClass(psiClassFromEditor);
    }
}
