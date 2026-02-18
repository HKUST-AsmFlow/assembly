// Generated using Parser Generator from BNF
// source: ARMv7.bnf
package io.github.asmflow.assembly.armv7.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static io.github.asmflow.assembly.armv7.psi.ARMv7TokenTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import io.github.asmflow.assembly.armv7.psi.*;
import io.github.asmflow.assembly.armv7.psi.util.ARMv7PsiImplUtil;

public class ARMv7NumbersImpl extends ASTWrapperPsiElement implements ARMv7Numbers {

  public ARMv7NumbersImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ARMv7Visitor visitor) {
    visitor.visitNumbers(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ARMv7Visitor) accept((ARMv7Visitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<ARMv7Number> getNumberList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ARMv7Number.class);
  }

}
