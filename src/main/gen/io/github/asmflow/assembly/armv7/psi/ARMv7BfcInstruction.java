// Generated using Parser Generator from BNF
// source: ARMv7.bnf
package io.github.asmflow.assembly.armv7.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ARMv7BfcInstruction extends PsiElement {

  @Nullable
  ARMv7ConditionCodes getConditionCodes();

  @NotNull
  ARMv7Numbers getNumbers();

  @NotNull
  ARMv7Register getRegister();

}
