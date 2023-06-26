package org.tnmk.tech_common.reflection;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;

public class ObjectConverter {
  /**
   * Guideline:
   * https://stackoverflow.com/questions/13943550/how-to-convert-from-string-to-a-primitive-type-or-standard-java-wrapper-types
   */
  public static <T> T convert(String str, Class<T> clazz) {
    // We can use ConvertUtils like this:
    // org.apache.commons.beanutils.ConvertUtils.convert("42", Integer.class);
    // However, it's not ideal because if conversion failed, it just silently return a default value instead of throwing error.

    PropertyEditor editor = PropertyEditorManager.findEditor(clazz);
    editor.setAsText(str);

    //noinspection unchecked
    return (T) editor.getValue();
  }
}
