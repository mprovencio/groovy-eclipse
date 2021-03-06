/*
 * Copyright 2003-2007 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.codehaus.groovy.ast;

import java.util.*;

/**
 * Base class for any AST node which is capable of being annotated
 *
 * @author <a href="mailto:jstrachan@protique.com">James Strachan</a>
 * @version $Revision: 15124 $
 */
public class AnnotatedNode extends ASTNode {
    private List annotations = Collections.EMPTY_LIST;
    private boolean synthetic;
    ClassNode declaringClass;
    // FIXASC (groovychange)
	private int nameEnd;
	private int nameStart;
	// end

    public AnnotatedNode() {
    }

    public List getAnnotations() {
        return annotations;
    }

    public List getAnnotations(ClassNode type) {
        List ret = new ArrayList(annotations.size());
        for (Iterator it = annotations.iterator(); it.hasNext();) {
            AnnotationNode node = (AnnotationNode) it.next();
            if (type.equals(node.getClassNode())) ret.add(node);
        }
        return ret;
    }

    public void addAnnotation(AnnotationNode value) {
        checkInit();
        annotations.add(value);
    }

    private void checkInit() {
        if (annotations == Collections.EMPTY_LIST)
          annotations = new ArrayList(3);
    }

    public void addAnnotations(List annotations) {
        for (Iterator iter = annotations.iterator(); iter.hasNext();) {
            AnnotationNode node = (AnnotationNode) iter.next();
            addAnnotation(node);
        }
    }

    /**
     * returns true if this node is added by the compiler.
     * <b>NOTE</b>: 
     * This method has nothing to do with the synthetic flag
     * for fields, methods or classes.              
     * @return true if this node is added by the compiler
     */
    public boolean isSynthetic() {
        return synthetic;
    }

    /**
     * sets this node as a node added by the compiler.
     * <b>NOTE</b>: 
     * This method has nothing to do with the synthetic flag
     * for fields, methods or classes.              
     * @param synthetic - if true this node is marked as
     *                    added by the compiler
     */
    public void setSynthetic(boolean synthetic) {
        this.synthetic = synthetic;
    }

    public ClassNode getDeclaringClass() {
        return declaringClass;
    }

    /**
     * @param declaringClass - The declaringClass to set.
     */
    public void setDeclaringClass(ClassNode declaringClass) {
        this.declaringClass = declaringClass;
    }

    // FIXASC (groovychange)
	public int getNameStart() {
		return nameStart;
	}

	public void setNameStart(int nameStart) {
		this.nameStart = nameStart;
	}

	public int getNameEnd() {
		return nameEnd;
	}

	public void setNameEnd(int nameEnd) {
		this.nameEnd = nameEnd;
	}
	// end
}
