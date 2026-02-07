// Generated using Parser Generator from BNF
// source: ARMv7.bnf
package io.github.asmflow.assembly.armv7.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ARMv7Item extends PsiElement {

  @Nullable
  ARMv7AdcInstruction getAdcInstruction();

  @Nullable
  ARMv7AddInstruction getAddInstruction();

  @Nullable
  ARMv7AlignDirective getAlignDirective();

  @Nullable
  ARMv7AsciiDirective getAsciiDirective();

  @Nullable
  ARMv7GlobalDirective getGlobalDirective();

  @Nullable
  ARMv7Label getLabel();

}
