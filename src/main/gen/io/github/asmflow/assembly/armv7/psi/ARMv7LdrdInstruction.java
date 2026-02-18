// Generated using Parser Generator from BNF
// source: ARMv7.bnf
package io.github.asmflow.assembly.armv7.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ARMv7LdrdInstruction extends IARMv7InstructionSuffix {

  @Nullable
  ARMv7Id getId();

  @Nullable
  ARMv7NoOffset getNoOffset();

  @Nullable
  ARMv7PCWithImmediateOffset getPCWithImmediateOffset();

  @Nullable
  ARMv7PostindexOffset getPostindexOffset();

  @Nullable
  ARMv7PreindexOffset getPreindexOffset();

  @NotNull
  ARMv7Registers getRegisters();

}
