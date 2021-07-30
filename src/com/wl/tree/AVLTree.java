package com.wl.tree;

@SuppressWarnings("ALl")
public class AVLTree<E> extends BBST<E> {

    @Override
    protected BinarySearchTree<E>.TreeNode<E> createNode() {
        return new AVLTreeNode<E>();
    }

    @Override
    protected void afterAdd(TreeNode<E> node) {
        AVLTreeNode<E> cur = ((AVLTreeNode) node);
        while (cur != null) {
            if (!cur.isBalanced()) {
                balanceNode(cur);
//                updateHeight(((AVLTreeNode<E>) node));
                break;
            } else {
                cur.updateHeight();
            }
            cur = ((AVLTreeNode) cur.parent);
        }
    }

    // 平衡二叉树
    private void balanceNode(AVLTreeNode<E> node) {
        AVLTreeNode<E> G = node;
        AVLTreeNode<E> P = G.balancedFactor() > 0 ? ((AVLTreeNode) G.left) : ((AVLTreeNode) G.right);
        AVLTreeNode<E> N = P.balancedFactor() > 0 ? ((AVLTreeNode) P.left) : ((AVLTreeNode) P.right);

        // rotate可以处理根节点的情况，故此处可忽略
        if (P.isLeftChild()) {
            if (N.isLeftChild()) {
                // LL
                rotateRight(G);
                G.updateHeight();
                P.updateHeight();
            } else {
                // LR
                rotateLeft(P);
                P.updateHeight();
                N.updateHeight();
                rotateRight(G);
                G.updateHeight();
                P.updateHeight();
            }
        } else {
            if (N.isRightChild()) {
                // RR
                rotateLeft(G);
                G.updateHeight();
                P.isRightChild();
            } else {
                // RL
                rotateRight(P);
                P.updateHeight();
                N.updateHeight();
                rotateLeft(G);
                G.updateHeight();
                P.updateHeight();
            }
        }
    }

    @Override
    protected void afterRemove(TreeNode<E> node) {
        if (node == null) {
            return;
        }
        afterAdd(node);
    }

    private class AVLTreeNode<E> extends TreeNode<E> {
        public int height;

        public void updateHeight() {
            int leftHeight = left == null ? 0 : ((AVLTreeNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLTreeNode<E>) right).height;
            height = Math.max(leftHeight, rightHeight) + 1;
        }

        public int balancedFactor() {
            int leftHeight = left == null ? 0 : ((AVLTreeNode<E>) left).height;
            int rightHeight = right == null ? 0 : ((AVLTreeNode<E>) right).height;
            return leftHeight - rightHeight;
        }

        public boolean isBalanced() {
            return Math.abs(balancedFactor()) <= 1;
        }
    }

}
