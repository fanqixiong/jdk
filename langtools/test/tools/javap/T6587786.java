/*
 * Copyright 2008 Sun Microsystems, Inc.  All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Sun Microsystems, Inc., 4150 Network Circle, Santa Clara,
 * CA 95054 USA or visit www.sun.com if you need additional information or
 * have any questions.
 */

/*
 * @test
 * @bug 6587786
 * @summary Javap throws error : "ERROR:Could not find <classname>" for JRE classes
 */

import java.io.*;

public class T6587786 {
    public static void main(String[] args) throws Exception {
        new T6587786().run();
    }

    public void run() throws IOException {
        javap("com.sun.javadoc.Doc", "com.sun.crypto.provider.ai");
        javap("com.sun.crypto.provider.ai", "com.sun.javadoc.ClassDoc");
    }

    void javap(String... args) {
        StringWriter sw = new StringWriter();
        PrintWriter out = new PrintWriter(sw);
        //sun.tools.javap.Main.entry(args);
        int rc = com.sun.tools.javap.Main.run(args, out);
        if (rc != 0)
            throw new Error("javap failed. rc=" + rc);
        out.close();
        System.out.println(sw.toString());
    }
}
