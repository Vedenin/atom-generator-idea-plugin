package com.github.vedenin.idea.plugins.atom.action;

import com.github.vedenin.atoms.collections.ListAtom;
import com.github.vedenin.atoms.pico.PicoContainerAtom;
import com.github.vedenin.idea.plugins.atom.factory.CreateBuilderDialogFactory;
import com.github.vedenin.idea.plugins.atom.finder.ClassFinder;
import com.github.vedenin.idea.plugins.atom.psi.BuilderPsiClassBuilder;
import com.github.vedenin.idea.plugins.atom.psi.PsiHelper;
import com.github.vedenin.idea.plugins.atom.writer.BuilderWriter;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.github.vedenin.idea.plugins.atom.action.handler.DisplayChoosersRunnable;
import com.github.vedenin.idea.plugins.atom.action.handler.GoToBuilderActionHandler;
import com.github.vedenin.idea.plugins.atom.factory.MemberChooserDialogFactory;
import com.github.vedenin.idea.plugins.atom.factory.PopupChooserBuilderFactory;
import com.github.vedenin.idea.plugins.atom.factory.PopupListFactory;
import com.github.vedenin.idea.plugins.atom.factory.PsiElementClassMemberFactory;
import com.github.vedenin.idea.plugins.atom.factory.PsiFieldsForBuilderFactory;
import com.github.vedenin.idea.plugins.atom.factory.PsiManagerFactory;
import com.github.vedenin.idea.plugins.atom.factory.ReferenceEditorComboWithBrowseButtonFactory;
import com.github.vedenin.idea.plugins.atom.finder.BuilderFinder;
import com.github.vedenin.idea.plugins.atom.gui.displayer.PopupDisplayer;
import com.github.vedenin.idea.plugins.atom.gui.helper.GuiHelper;
import com.github.vedenin.idea.plugins.atom.psi.PsiFieldSelector;
import com.github.vedenin.idea.plugins.atom.verifier.BuilderVerifier;
import com.github.vedenin.idea.plugins.atom.verifier.PsiFieldVerifier;


public class GoToBuilderAction extends EditorAction {
    protected GoToBuilderAction() {
        super(getGoToBuilderActionHandler());
    }

    private static GoToBuilderActionHandler getGoToBuilderActionHandler() {
        PicoContainerAtom picoContainer = PicoContainerAtom.createAtom(
                ListAtom.create(
                        PsiHelper.class,
                        BuilderVerifier.class,
                        ClassFinder.class,
                        BuilderPsiClassBuilder.class,
                        BuilderFinder.class,
                        PopupChooserBuilderFactory.class,
                        PopupDisplayer.class,
                        PopupListFactory.class,
                        PsiManagerFactory.class,
                        CreateBuilderDialogFactory.class,
                        GuiHelper.class,
                        PsiFieldVerifier.class,
                        PsiElementClassMemberFactory.class,
                        ReferenceEditorComboWithBrowseButtonFactory.class,
                        MemberChooserDialogFactory.class,
                        BuilderWriter.class,
                        PsiFieldSelector.class,
                        PsiFieldsForBuilderFactory.class,
                        GoToBuilderActionHandler.class,
                        DisplayChoosersRunnable.class
                )
        );
        return picoContainer.getComponent(GoToBuilderActionHandler.class);
    }
}
