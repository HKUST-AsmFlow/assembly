// Generated using Parser Generator from BNF
// source: ARMv7.bnf
package io.github.asmflow.assembly.armv7.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ARMv7Operand extends ARMv7OperandMixin {

  @Nullable
  ARMv7Label getLabel();

  @Nullable
  ARMv7LiteralLoad getLiteralLoad();

  @Nullable
  ARMv7Number getNumber();

  @Nullable
  ARMv7Postindexed getPostindexed();

  @Nullable
  ARMv7Preindexed getPreindexed();

  @Nullable
  ARMv7RegisterList getRegisterList();

  @Nullable
  ARMv7RegisterWithShift getRegisterWithShift();

}
