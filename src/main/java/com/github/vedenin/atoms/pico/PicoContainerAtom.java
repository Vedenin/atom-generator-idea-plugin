package com.github.vedenin.atoms.pico;

import com.github.vedenin.atom.annotations.Atom;

import com.github.vedenin.atom.annotations.AtomException;
import org.picocontainer.MutablePicoContainer;
import org.picocontainer.defaults.DefaultPicoContainer;

@Atom({MutablePicoContainer.class, DefaultPicoContainer.class})
@AtomException(PicoContainerAtom.class)
public class PicoContainerAtom {
    private MutablePicoContainer original;

    public PicoContainerAtom(MutablePicoContainer original) {
        this.original = original;
    }

    public void registerComponentImplementation(Class var1) {
        try {
            original.registerComponentImplementation(var1);
        } catch (RuntimeException exp) {
            throw new PicoContainerAtomException(exp);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T getComponentInstanceOfType(Class<T> var1) {
        try {
            return (T) original.getComponentInstanceOfType(var1);
        } catch (RuntimeException exp) {
            throw new PicoContainerAtomException(exp);
        }
    }

    public static PicoContainerAtom createDefault() {
        return new PicoContainerAtom(new DefaultPicoContainer());
    }
}
