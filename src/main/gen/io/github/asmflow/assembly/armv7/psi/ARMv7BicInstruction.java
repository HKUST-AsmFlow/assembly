// Generated using Parser Generator from BNF
// source: ARMv7.bnf
package io.github.asmflow.assembly.armv7.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ARMv7BicInstruction extends IARMv7InstructionSuffix {

  @Nullable
  ARMv7Number getNumber();

  @NotNull
  ARMv7Registers getRegisters();

  @Nullable
  ARMv7Shift getShift();

}
