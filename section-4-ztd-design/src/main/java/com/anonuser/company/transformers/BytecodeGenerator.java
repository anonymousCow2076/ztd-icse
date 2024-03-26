package com.anonuser.company.transformers;

import com.anonuser.company.adapters.ASMifierAdapter;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

public class BytecodeGenerator implements ClassFileTransformer {

    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {
        if(className.equals("com/anonuser/company/apps/DumpBytecodeInnerClass")) {

            System.out.println("DumpBytecodeInnerClass class found");
            ClassReader classReader = new ClassReader(classfileBuffer);
            ClassWriter classWriter = new ClassWriter(classReader, 0);

            ASMifierAdapter adapter = new ASMifierAdapter(classWriter);
            classReader.accept(adapter, 0);
            return classWriter.toByteArray();

        }
        return classfileBuffer;

    }
}
