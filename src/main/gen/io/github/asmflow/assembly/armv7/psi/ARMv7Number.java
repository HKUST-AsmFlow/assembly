// Generated using Parser Generator from BNF
// source: ARMv7.bnf
package io.github.asmflow.assembly.armv7.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ARMv7Number extends ARMv7NumberMixin, ARMv7OperandMixin {

  @Nullable
  ARMv7BinaryLiteral getBinaryLiteral();

  @Nullable
  ARMv7DecimalLiteral getDecimalLiteral();

  @Nullable
  ARMv7HexadecimalLiteral getHexadecimalLiteral();

  @Nullable
  ARMv7OctalLiteral getOctalLiteral();

  @Nullable
  ARMv7Sign getSign();

}
