package com.github.magickoders.jar;

import com.github.forax.umldoc.core.Entity;
import com.github.forax.umldoc.core.Field;
import com.github.forax.umldoc.core.Method;
import com.github.forax.umldoc.core.Modifier;
import com.github.forax.umldoc.core.TypeInfo;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Builder for com.github.forax.umldoc.core.Entity. It helps build an entity when you need to add
 * fields and methods multiple times on the same entity.
 */
public class EntityBuilder {

  private final ArrayList<Method> methods = new ArrayList<>();
  private final ArrayList<Field> fields = new ArrayList<>();
  private TypeInfo type = null;
  private Set<Modifier> modifiers = null;
  private Entity.Stereotype stereotype = null;

  /**
   * Adds a method to the entity to build.
   *
   * @param m
   *         method to add on the buildable entity
   * @return a reference to this object
   */
  public EntityBuilder addMethod(Method m) {
    Objects.requireNonNull(m);
    this.methods.add(m);
    return this;
  }

  /**
   * Adds a field to the entity to build.
   *
   * @param f
   *         field to add on the buildable entity
   * @return a reference to this object
   */
  public EntityBuilder addField(Field f) {
    Objects.requireNonNull(f);
    this.fields.add(f);
    return this;
  }

  /**
   * Sets all modifiers of the entity to build.
   *
   * @param modifiers
   *         a set of Modifier
   * @return a reference to this object
   */
  public EntityBuilder setModifiers(Set<Modifier> modifiers) {
    this.modifiers = Set.copyOf(modifiers);
    return this;
  }

  /**
   * Sets the type of the entity to build.
   *
   * @param type
   *         a TypeInfo representing the "class" type
   * @return a reference to this object
   */
  public EntityBuilder setTypeInfo(TypeInfo type) {
    Objects.requireNonNull(type);
    this.type = type;
    return this;
  }

  /**
   * Sets the stereotype of the entity to build.
   *
   * @param stereotype
   *         an Entity.Stereotype representing the type of the class (e.i. enum, record, ...)
   * @return a reference to this object
   */
  public EntityBuilder setStereotype(Entity.Stereotype stereotype) {
    Objects.requireNonNull(stereotype);
    this.stereotype = stereotype;
    return this;
  }


  /**
   * Resets the builder to its original state by resetting all values to their default. The builder
   * cannot build entity after being cleared.
   */
  private void clear() {
    modifiers = null;
    type = null;
    stereotype = null;
    methods.clear();
    fields.clear();
  }

  /**
   * Builds the entity with the previously set values. If the entity cannot be built it still clears
   * the values and returns an empty Optional.
   *
   * @return the built entity wrapped inside an Optional or an empty Optional.
   * @see #setModifiers(Set)
   * @see #setTypeInfo(TypeInfo)
   * @see #setStereotype(Entity.Stereotype)
   * @see #addField(Field)
   * @see #addMethod(Method)
   */
  public Optional<Entity> build() {
    if (modifiers == null || type == null || stereotype == null) {
      clear();
      return Optional.empty();
    }
    var entity = new Entity(modifiers, type, stereotype, fields, methods);
    clear();
    return Optional.of(entity);
  }

}
