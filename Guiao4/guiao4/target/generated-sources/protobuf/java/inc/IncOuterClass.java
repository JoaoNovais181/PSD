// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: inc.proto

// Protobuf Java Version: 3.25.3
package inc;

public final class IncOuterClass {
  private IncOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_inc_Message_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_inc_Message_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\tinc.proto\022\003inc\"\026\n\007Message\022\013\n\003num\030\001 \001(\005" +
      "20\n\003Inc\022)\n\007incMany\022\014.inc.Message\032\014.inc.M" +
      "essage(\0010\001B\002P\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_inc_Message_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_inc_Message_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_inc_Message_descriptor,
        new java.lang.String[] { "Num", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
