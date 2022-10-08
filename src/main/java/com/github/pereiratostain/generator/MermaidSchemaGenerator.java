package com.github.pereiratostain.generator;

import static java.util.Objects.requireNonNull;

import com.github.forax.umldoc.core.Entity;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Generate a schema in the Mermaid format.
 */
public class MermaidSchemaGenerator implements Generator {

  private final Writer writer;

  /**
   * Creates a MermaidSchemaGenerator that will write in the given writer.
   *
   * @param writer The writer in which the generator must write.
   */
  public MermaidSchemaGenerator(Writer writer) {
    requireNonNull(writer);

    this.writer = writer;
  }

  @Override
  public void generate(List<Entity> entities) throws IOException {
    requireNonNull(entities);

    generateHeader();
    for (var entity : entities) {
      generateEntity(entity);
    }
  }

  private void generateHeader() throws IOException {
    writer.append("""
            classDiagram
                direction TB

            """);
  }

  private void generateEntity(Entity entity) throws IOException {
    writer.append("    class ")
            .append(entity.name())
            .append(" {")
            .append("\n    }\n\n");
  }
}
