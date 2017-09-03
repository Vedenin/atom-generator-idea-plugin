package com.github.vedenin.atoms.pico;

import com.github.vedenin.atom.annotations.AtomException;

@AtomException(PicoContainerAtom.class)
public class PicoContainerAtomException extends RuntimeException {
    public PicoContainerAtomException(Exception exp) {
        super(exp);
    }
}
