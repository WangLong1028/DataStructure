package com.wl.tree;

@SuppressWarnings("All")
public class BBST<E> extends BinarySearchTree<E> {

    protected void rotateRight(TreeNode<E> node) {
        //            |                          |
        //            G                          P
        //       P        GR    ---->       N          G
        //    N    PR                    NL   NR    PR    GR
        //  NL NR
        TreeNode<E> G = node;
        TreeNode<E> P = G.left;
        TreeNode<E> PR = P.right;

        TreeNode<E> parent = G.parent;

        // 处理P
        if (parent == null) {
            // 说明G是根节点
            root = P;
            P.parent = null;
        } else {
            connectParentAndChild(parent, P, G.isLeftChild());
        }

        // 处理G
        P.right = G;
        G.parent = P;
        G.left = null;
        if (PR != null) connectParentAndChild(G, PR, true);
    }

    protected void rotateLeft(TreeNode<E> node) {
        //            |                          |
        //            G                          P
        //       GL        P    ---->       G          N
        //              PL    N           GL  PL     NL NR
        //                  NL  NR
        TreeNode<E> G = node;
        TreeNode<E> P = G.right;
        TreeNode<E> PL = P.left;

        TreeNode<E> parent = G.parent;

        // 处理P
        if (parent == null) {
            // 说明G是根节点
            root = P;
            P.parent = null;
        } else {
            connectParentAndChild(parent, P, G.isLeftChild());
        }

        // 处理G
        P.left = G;
        G.parent = P;
        G.right = null;
        if (PL != null) connectParentAndChild(G, PL, false);
    }
}
