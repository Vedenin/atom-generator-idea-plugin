package com.github.vedenin.atoms.pico;

import com.github.vedenin.atom.annotations.Atom;

import com.github.vedenin.atom.annotations.AtomException;
import com.github.vedenin.atoms.collections.ListAtom;
import com.github.vedenin.idea.plugins.atom.psi.PsiHelper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.defaults.DefaultPicoContainer;

import java.util.List;

@Atom({MutablePicoContainer.class, DefaultPicoContainer.class})
@AtomException(PicoContainerAtom.class)
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PicoContainerAtom {
    private final MutablePicoContainer original;

    public void registerComponentImplementation(Class var1) {
        try {
            original.registerComponentImplementation(var1);
        } catch (RuntimeException exp) {
            throw new PicoContainerAtomException(exp);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getComponent(Class<T> var1) {
        try {
            return (T) original.getComponentInstanceOfType(var1);
        } catch (RuntimeException exp) {
            throw new PicoContainerAtomException(exp);
        }
    }

    public static PicoContainerAtom createAtom() {
        return new PicoContainerAtom(new DefaultPicoContainer());
    }

    public static PicoContainerAtom createAtom(ListAtom<Class> list) {
        MutablePicoContainer container = new DefaultPicoContainer();
        list.forEach(container::registerComponentImplementation);
        return new PicoContainerAtom(container);
    }
}
