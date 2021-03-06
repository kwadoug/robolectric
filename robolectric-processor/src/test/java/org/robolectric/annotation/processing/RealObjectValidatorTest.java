package org.robolectric.annotation.processing;

import static com.google.testing.compile.JavaFileObjects.forResource;
import static com.google.testing.compile.JavaSourcesSubjectFactory.javaSources;
import static org.truth0.Truth.ASSERT;
import static org.robolectric.annotation.processing.Utils.ROBO_INTERNALS_SOURCE;
import static org.robolectric.annotation.processing.Utils.SHADOW_WRANGLER_SOURCE;
import static org.robolectric.annotation.processing.SingleClassSubject.singleClass;

import org.junit.Test;

import com.google.common.collect.ImmutableList;

public class RealObjectValidatorTest {
  @Test
  public void realObjectWithoutImplements_shouldNotCompile() {
    final String testClass = "org.robolectric.annotation.processing.shadows.ShadowRealObjectWithoutImplements";
    ASSERT.about(singleClass())
      .that(testClass)
      .failsToCompile()
      .withErrorContaining("@RealObject without @Implements")
      .onLine(7);
  }

  @Test
  public void realObjectWithEmptyImplements_shouldNotRaiseOwnError() {
    final String testClass = "org.robolectric.annotation.processing.shadows.ShadowRealObjectWithEmptyImplements";
    ASSERT.about(singleClass())
      .that(testClass)
      .failsToCompile()
      .withNoErrorContaining("@RealObject");
  }

  @Test
  public void realObjectWithMissingClassName_shouldNotRaiseOwnError() {
    final String testClass = "org.robolectric.annotation.processing.shadows.ShadowRealObjectWithMissingClassName";
    ASSERT.about(singleClass())
      .that(testClass)
      .failsToCompile()
      .withNoErrorContaining("@RealObject");
  }

  @Test
  public void realObjectWithEmptyClassNameNoAnything_shouldNotRaiseOwnError() {
    final String testClass = "org.robolectric.annotation.processing.shadows.ShadowRealObjectWithEmptyClassNameNoAnything";
    ASSERT.about(singleClass())
      .that(testClass)
      .failsToCompile()
      .withNoErrorContaining("@RealObject");
  }

  @Test
  public void realObjectWithTypeMismatch_shouldNotCompile() {
    final String testClass = "org.robolectric.annotation.processing.shadows.ShadowRealObjectWithWrongType";
    ASSERT.about(singleClass())
      .that(testClass)
      .failsToCompile()
      .withErrorContaining("@RealObject with type <org.robolectric.annotation.processing.objects.UniqueDummy>; expected <org.robolectric.annotation.processing.objects.Dummy>")
      .onLine(11);
  }

  @Test
  public void realObjectWithClassName_typeMismatch_shouldNotCompile() {
    final String testClass = "org.robolectric.annotation.processing.shadows.ShadowRealObjectWithIncorrectClassName";
    ASSERT.about(singleClass())
      .that(testClass)
      .failsToCompile()
      .withErrorContaining("@RealObject with type <org.robolectric.annotation.processing.objects.UniqueDummy>; expected <org.robolectric.annotation.processing.objects.Dummy>")
      .onLine(10);
  }

  @Test
  public void realObjectWithCorrectType_shouldCompile() {
    final String testClass = "org.robolectric.annotation.processing.shadows.ShadowRealObjectWithCorrectType";
    ASSERT.about(singleClass())
      .that(testClass)
      .compilesWithoutError();
  }

  @Test
  public void realObjectWithCorrectType_withoutAnything_shouldCompile() {
    ASSERT.about(javaSources())
    .that(ImmutableList.of(
        ROBO_INTERNALS_SOURCE,
        SHADOW_WRANGLER_SOURCE,
        forResource("org/robolectric/annotation/processing/shadows/ShadowRealObjectWithCorrectType.java")))
    .processedWith(new RoboProcessor())
      .compilesWithoutError();
  }

  @Test
  public void realObjectWithCorrectAnything_shouldCompile() {
    final String testClass = "org.robolectric.annotation.processing.shadows.ShadowRealObjectWithCorrectAnything";
    ASSERT.about(singleClass())
      .that(testClass)
      .compilesWithoutError();
  }

  @Test
  public void shouldGracefullyHandleNoAnythingClass_withFoundOnImplementsAnnotation() {
  }

  @Test
  public void realObjectWithCorrectClassName_shouldCompile() {
    ASSERT.about(javaSources())
      .that(ImmutableList.of(
          ROBO_INTERNALS_SOURCE,
          SHADOW_WRANGLER_SOURCE,
          forResource("org/robolectric/annotation/processing/shadows/ShadowRealObjectWithCorrectClassName.java")))
      .processedWith(new RoboProcessor())
      .compilesWithoutError();
  }
  
  @Test
  public void realObjectWithNestedClassName_shouldCompile() {
    final String testClass = "org.robolectric.annotation.processing.shadows.ShadowRealObjectWithNestedClassName";
    ASSERT.about(singleClass())
      .that(testClass)
      .compilesWithoutError();
  }
}
