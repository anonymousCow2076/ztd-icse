// [PROFILING] AGENT_CALLED 1691018767323
//     Permissions Agent
//     [PROFILING] AGENT_EXITING 1691018767700
//     DumpExecBytecodeApp class found
// package asm.com.anonuser.company.apps;
//     import org.objectweb.asm.AnnotationVisitor;
//     import org.objectweb.asm.Attribute;
//     import org.objectweb.asm.ClassReader;
//     import org.objectweb.asm.ClassWriter;
//     import org.objectweb.asm.ConstantDynamic;
//     import org.objectweb.asm.FieldVisitor;
//     import org.objectweb.asm.Handle;
//     import org.objectweb.asm.Label;
//     import org.objectweb.asm.MethodVisitor;
//     import org.objectweb.asm.Opcodes;
//     import org.objectweb.asm.RecordComponentVisitor;
//     import org.objectweb.asm.Type;
//     import org.objectweb.asm.TypePath;
// public class DumpBytecodeAppDump implements Opcodes {
//
//     public static byte[] dump () throws Exception {
//
//         ClassWriter classWriter = new ClassWriter(0);
//         FieldVisitor fieldVisitor;
//         RecordComponentVisitor recordComponentVisitor;
//         MethodVisitor methodVisitor;
//         AnnotationVisitor annotationVisitor0;
//
//         classWriter.visit(V1_8, ACC_PUBLIC | ACC_SUPER, "com/anonuser/company/apps/DumpBytecodeApp", null, "java/lang/Object", null);
//
//         classWriter.visitSource("DumpBytecodeApp.java", null);
//
//         {
//             methodVisitor = classWriter.visitMethod(ACC_PUBLIC, "<init>", "()V", null, null);
//             methodVisitor.visitCode();
//             Label label0 = new Label();
//             methodVisitor.visitLabel(label0);
//             methodVisitor.visitLineNumber(8, label0);
//             methodVisitor.visitVarInsn(ALOAD, 0);
//             methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/lang/Object", "<init>", "()V", false);
//             methodVisitor.visitInsn(RETURN);
//             Label label1 = new Label();
//             methodVisitor.visitLabel(label1);
//             methodVisitor.visitLocalVariable("this", "Lcom/anonuser/company/apps/DumpBytecodeApp;", null, label0, label1, 0);
//             methodVisitor.visitMaxs(1, 1);
//             methodVisitor.visitEnd();
//         }
//         {
//             methodVisitor = classWriter.visitMethod(ACC_PUBLIC | ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
//             methodVisitor.visitCode();
//             Label label0 = new Label();
//             Label label1 = new Label();
//             Label label2 = new Label();
//             methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/IllegalAccessException");
//             methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/NoSuchMethodException");
//             methodVisitor.visitTryCatchBlock(label0, label1, label2, "java/lang/ClassNotFoundException");
//             Label label3 = new Label();
//             methodVisitor.visitTryCatchBlock(label0, label1, label3, "java/lang/reflect/InvocationTargetException");
//             Label label4 = new Label();
//             methodVisitor.visitLabel(label4);
//             methodVisitor.visitLineNumber(12, label4);
//             methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
//             methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Thread", "getStackTrace", "()[Ljava/lang/StackTraceElement;", false);
//             methodVisitor.visitVarInsn(ASTORE, 1);
//             Label label5 = new Label();
//             methodVisitor.visitLabel(label5);
//             methodVisitor.visitLineNumber(13, label5);
//             methodVisitor.visitVarInsn(ALOAD, 1);
//             methodVisitor.visitInsn(ICONST_1);
//             methodVisitor.visitInsn(AALOAD);
//             methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/StackTraceElement", "getClassName", "()Ljava/lang/String;", false);
//             methodVisitor.visitVarInsn(ASTORE, 2);
//             Label label6 = new Label();
//             methodVisitor.visitLabel(label6);
//             methodVisitor.visitLineNumber(16, label6);
//             methodVisitor.visitVarInsn(ALOAD, 2);
//             methodVisitor.visitLdcInsn("jdk.internal.loader");
//             methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "startsWith", "(Ljava/lang/String;)Z", false);
//             Label label7 = new Label();
//             methodVisitor.visitJumpInsn(IFNE, label7);
//             methodVisitor.visitVarInsn(ALOAD, 2);
//             methodVisitor.visitLdcInsn("sun.misc.URLClassPath$FileLoader");
//             methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/String", "startsWith", "(Ljava/lang/String;)Z", false);
//             methodVisitor.visitJumpInsn(IFNE, label7);
//             methodVisitor.visitLabel(label0);
//             methodVisitor.visitLineNumber(18, label0);
//             methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/ClassLoader", "getSystemClassLoader", "()Ljava/lang/ClassLoader;", false);
//             methodVisitor.visitLdcInsn("com.anonuser.company.PermissionsManager");
//             methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/ClassLoader", "loadClass", "(Ljava/lang/String;)Ljava/lang/Class;", false);
//             methodVisitor.visitVarInsn(ASTORE, 3);
//             Label label8 = new Label();
//             methodVisitor.visitLabel(label8);
//             methodVisitor.visitLineNumber(19, label8);
//             methodVisitor.visitVarInsn(ALOAD, 3);
//             methodVisitor.visitLdcInsn("mockTest");
//             methodVisitor.visitInsn(ICONST_3);
//             methodVisitor.visitTypeInsn(ANEWARRAY, "java/lang/Class");
//             methodVisitor.visitInsn(DUP);
//             methodVisitor.visitInsn(ICONST_0);
//             methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/Integer", "TYPE", "Ljava/lang/Class;");
//             methodVisitor.visitInsn(AASTORE);
//             methodVisitor.visitInsn(DUP);
//             methodVisitor.visitInsn(ICONST_1);
//             methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/Integer", "TYPE", "Ljava/lang/Class;");
//             methodVisitor.visitInsn(AASTORE);
//             methodVisitor.visitInsn(DUP);
//             methodVisitor.visitInsn(ICONST_2);
//             methodVisitor.visitLdcInsn(Type.getType("Ljava/lang/String;"));
//             methodVisitor.visitInsn(AASTORE);
//             methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/Class", "getMethod", "(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;", false);
//             methodVisitor.visitVarInsn(ASTORE, 4);
//             Label label9 = new Label();
//             methodVisitor.visitLabel(label9);
//             methodVisitor.visitLineNumber(20, label9);
//             methodVisitor.visitLdcInsn("filename");
//             methodVisitor.visitVarInsn(ASTORE, 5);
//             Label label10 = new Label();
//             methodVisitor.visitLabel(label10);
//             methodVisitor.visitLineNumber(21, label10);
//             methodVisitor.visitVarInsn(ALOAD, 4);
//             methodVisitor.visitInsn(ACONST_NULL);
//             methodVisitor.visitInsn(ICONST_3);
//             methodVisitor.visitTypeInsn(ANEWARRAY, "java/lang/Object");
//             methodVisitor.visitInsn(DUP);
//             methodVisitor.visitInsn(ICONST_0);
//             methodVisitor.visitInsn(ICONST_0);
//             methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
//             methodVisitor.visitInsn(AASTORE);
//             methodVisitor.visitInsn(DUP);
//             methodVisitor.visitInsn(ICONST_1);
//             methodVisitor.visitInsn(ICONST_0);
//             methodVisitor.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
//             methodVisitor.visitInsn(AASTORE);
//             methodVisitor.visitInsn(DUP);
//             methodVisitor.visitInsn(ICONST_2);
//             methodVisitor.visitTypeInsn(NEW, "java/io/File");
//             methodVisitor.visitInsn(DUP);
//             methodVisitor.visitVarInsn(ALOAD, 5);
//             methodVisitor.visitMethodInsn(INVOKESPECIAL, "java/io/File", "<init>", "(Ljava/lang/String;)V", false);
//             methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/File", "getAbsolutePath", "()Ljava/lang/String;", false);
//             methodVisitor.visitInsn(AASTORE);
//             methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/Method", "invoke", "(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;", false);
//             methodVisitor.visitInsn(POP);
//             methodVisitor.visitLabel(label1);
//             methodVisitor.visitLineNumber(31, label1);
//             methodVisitor.visitJumpInsn(GOTO, label7);
//             methodVisitor.visitLabel(label2);
//             methodVisitor.visitLineNumber(22, label2);
//             methodVisitor.visitFrame(Opcodes.F_FULL, 3, new Object[] {"[Ljava/lang/String;", "[Ljava/lang/StackTraceElement;", "java/lang/String"}, 1, new Object[] {"java/lang/ReflectiveOperationException"});
//             methodVisitor.visitVarInsn(ASTORE, 3);
//             Label label11 = new Label();
//             methodVisitor.visitLabel(label11);
//             methodVisitor.visitLineNumber(23, label11);
//             methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//             methodVisitor.visitLdcInsn("Exception thrown in instrumentation");
//             methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
//             Label label12 = new Label();
//             methodVisitor.visitLabel(label12);
//             methodVisitor.visitLineNumber(24, label12);
//             methodVisitor.visitFieldInsn(GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
//             methodVisitor.visitVarInsn(ALOAD, 3);
//             methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/Object;)V", false);
//             Label label13 = new Label();
//             methodVisitor.visitLabel(label13);
//             methodVisitor.visitLineNumber(31, label13);
//             methodVisitor.visitJumpInsn(GOTO, label7);
//             methodVisitor.visitLabel(label3);
//             methodVisitor.visitLineNumber(25, label3);
//             methodVisitor.visitFrame(Opcodes.F_SAME1, 0, null, 1, new Object[] {"java/lang/reflect/InvocationTargetException"});
//             methodVisitor.visitVarInsn(ASTORE, 3);
//             Label label14 = new Label();
//             methodVisitor.visitLabel(label14);
//             methodVisitor.visitLineNumber(26, label14);
//             methodVisitor.visitVarInsn(ALOAD, 3);
//             methodVisitor.visitMethodInsn(INVOKEVIRTUAL, "java/lang/reflect/InvocationTargetException", "getCause", "()Ljava/lang/Throwable;", false);
//             methodVisitor.visitVarInsn(ASTORE, 4);
//             Label label15 = new Label();
//             methodVisitor.visitLabel(label15);
//             methodVisitor.visitLineNumber(28, label15);
//             methodVisitor.visitVarInsn(ALOAD, 4);
//             methodVisitor.visitTypeInsn(INSTANCEOF, "java/lang/SecurityException");
//             methodVisitor.visitJumpInsn(IFEQ, label7);
//             Label label16 = new Label();
//             methodVisitor.visitLabel(label16);
//             methodVisitor.visitLineNumber(29, label16);
//             methodVisitor.visitVarInsn(ALOAD, 4);
//             methodVisitor.visitTypeInsn(CHECKCAST, "java/lang/SecurityException");
//             methodVisitor.visitInsn(ATHROW);
//             methodVisitor.visitLabel(label7);
//             methodVisitor.visitLineNumber(33, label7);
//             methodVisitor.visitFrame(Opcodes.F_SAME, 0, null, 0, null);
//             methodVisitor.visitInsn(RETURN);
//             Label label17 = new Label();
//             methodVisitor.visitLabel(label17);
//             methodVisitor.visitLocalVariable("permManagerClass", "Ljava/lang/Class;", "Ljava/lang/Class<*>;", label8, label1, 3);
//             methodVisitor.visitLocalVariable("logMethod", "Ljava/lang/reflect/Method;", null, label9, label1, 4);
//             methodVisitor.visitLocalVariable("name", "Ljava/lang/String;", null, label10, label1, 5);
//             methodVisitor.visitLocalVariable("e", "Ljava/lang/ReflectiveOperationException;", null, label11, label13, 3);
//             methodVisitor.visitLocalVariable("cause", "Ljava/lang/Throwable;", null, label15, label7, 4);
//             methodVisitor.visitLocalVariable("e", "Ljava/lang/reflect/InvocationTargetException;", null, label14, label7, 3);
//             methodVisitor.visitLocalVariable("args", "[Ljava/lang/String;", null, label4, label17, 0);
//             methodVisitor.visitLocalVariable("stackTrace", "[Ljava/lang/StackTraceElement;", null, label5, label17, 1);
//             methodVisitor.visitLocalVariable("callerName", "Ljava/lang/String;", null, label6, label17, 2);
//             methodVisitor.visitMaxs(8, 6);
//             methodVisitor.visitEnd();
//         }
//         classWriter.visitEnd();
//
//         return classWriter.toByteArray();
//     }
// }