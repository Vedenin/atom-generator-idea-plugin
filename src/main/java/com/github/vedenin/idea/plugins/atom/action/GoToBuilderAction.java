package com.github.vedenin.idea.plugins.atom.action;

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

    private static GoToBuilderActionHandler goToBuilderActionHandler;

    private static PicoContainerAtom picoContainer = PicoContainerAtom.createDefault();

    static {
        picoContainer.registerComponentImplementation(PsiHelper.class);
        picoContainer.registerComponentImplementation(BuilderVerifier.class);
        picoContainer.registerComponentImplementation(ClassFinder.class);
        picoContainer.registerComponentImplementation(BuilderPsiClassBuilder.class);
        picoContainer.registerComponentImplementation(BuilderFinder.class);
        picoContainer.registerComponentImplementation(PopupChooserBuilderFactory.class);
        picoContainer.registerComponentImplementation(PopupDisplayer.class);
        picoContainer.registerComponentImplementation(PopupListFactory.class);
        picoContainer.registerComponentImplementation(PsiManagerFactory.class);
        picoContainer.registerComponentImplementation(CreateBuilderDialogFactory.class);
        picoContainer.registerComponentImplementation(GuiHelper.class);
        picoContainer.registerComponentImplementation(PsiFieldVerifier.class);
        picoContainer.registerComponentImplementation(PsiElementClassMemberFactory.class);
        picoContainer.registerComponentImplementation(ReferenceEditorComboWithBrowseButtonFactory.class);
        picoContainer.registerComponentImplementation(MemberChooserDialogFactory.class);
        picoContainer.registerComponentImplementation(BuilderWriter.class);
        picoContainer.registerComponentImplementation(PsiFieldSelector.class);
        picoContainer.registerComponentImplementation(PsiFieldsForBuilderFactory.class);
        picoContainer.registerComponentImplementation(GoToBuilderActionHandler.class);
        picoContainer.registerComponentImplementation(DisplayChoosersRunnable.class);

        goToBuilderActionHandler = picoContainer.getComponentInstanceOfType(GoToBuilderActionHandler.class);
    }


    protected GoToBuilderAction() {
        super(goToBuilderActionHandler);
    }
}
