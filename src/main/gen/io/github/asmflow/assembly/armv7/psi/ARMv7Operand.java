// Generated using Parser Generator from BNF
// source: ARMv7.bnf
package io.github.asmflow.assembly.armv7.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ARMv7Operand extends PsiElement {

  @Nullable
  ARMv7Label getLabel();

  @Nullable
  ARMv7Number getNumber();

  @Nullable
  ARMv7Offset getOffset();

  @Nullable
  ARMv7OffsetVariant getOffsetVariant();

  @Nullable
  ARMv7Postindexed getPostindexed();

  @Nullable
  ARMv7Preindexed getPreindexed();

  @Nullable
  ARMv7Register getRegister();

  @Nullable
  ARMv7RegisterWithShift getRegisterWithShift();

}
