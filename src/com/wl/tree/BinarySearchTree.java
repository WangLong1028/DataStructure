package com.wl.tree;


import com.wl.queue.Queue;
import com.wl.tree.printer.BinaryTreeInfo;
import com.wl.utils.Comparator;

@SuppressWarnings("All")
public class BinarySearchTree<E> implements IBinarySearchTree<E>, BinaryTreeInfo {

    // 元素数量
    private int size;

    // 根节点
    protected TreeNode<E> root;

    // 比较器
    private Comparator<E> comparator;

    // 遍历模式
    // 中序遍历
    public static final int TRAVERSAL_MODE_LDR = 0;
    // 前序遍历
    public static final int TRAVERSAL_MODE_DLR = 1;
    // 后序遍历
    public static final int TRAVERSAL_MODE_LRD = 2;
    // 层序遍历
    public static final int TRAVERSAL_MODE_LAYER = 3;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void add(E e) {
        size++;
        if (root == null) {
            root = createNode();
            root.value = e;
            afterAdd(root);
            return;
        }
        TreeNode<E> parent = root;
        TreeNode<E> newNode = createNode();
        newNode.value = e;
        while (true) {
            int cmp = compare(e, parent.value);
            if (cmp > 0) {
                if (!parent.hasRight()) {
                    connectParentAndChild(parent, newNode, false);
                    afterAdd(newNode);
                    return;
                }
                parent = parent.right;
                continue;
            } else if (cmp == 0) {
                // 相同则覆盖
                size--;
                parent.value = e;
                return;
            } else {
                if (!parent.hasLeft()) {
                    connectParentAndChild(parent, newNode, true);
                    afterAdd(newNode);
                    return;
                }
                parent = parent.left;
                continue;
            }
        }
    }

    public void addArray(E[] array) {
        for (E e : array) {
            add(e);
        }
    }

    @Override
    public void remove(E e) {
        DLRTraversal(root, new PrivateVisitor() {
            @Override
            public boolean view(TreeNode<E> treeNode) {
                if (treeNode.value.equals(e)) {
                    removeNode(treeNode);
                    return true;
                }
                return false;
            }
        });
    }

    // 是否使用前驱节点取代
    private boolean isUsePrecursor = true;

    private void removeNode(TreeNode<E> node) {
        if (!node.hasChild()) {
            // 如果是叶子节点
            TreeNode<E> parent = node.parent;
            if (parent == null){
                // 如果是根节点
                root = null;
                isUsePrecursor = true;
                size--;
                afterRemove(parent);
                return;
            }
            disconnectParentAndChild(parent, node, node.isLeftChild());
            // 设置默认前驱节点
            isUsePrecursor = true;
            size--;
            afterRemove(parent);
            return;
        }
        // 不是叶子节点
        // 寻找取代节点，默认前驱节点
        TreeNode<E> replaceNode = precursor(node);
        if (replaceNode == null) {
            // 如果取代的节点为空
            // 则根据情况选择前驱/后继节点
            replaceNode = isUsePrecursor ? successor(node) : precursor(node);
            isUsePrecursor = !isUsePrecursor;
        }
        node.value = replaceNode.value;
        removeNode(replaceNode);
    }

    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    @Override
    public boolean contain(E e) {
        if (root == null) {
            return false;
        }
        PrivateVisitor found = new PrivateVisitor() {
            @Override
            public boolean view(TreeNode<E> treeNode) {
                if (treeNode.value.equals(e)) {
                    return true;
                }
                return false;
            }
        };
        DLRTraversal(root, found);
        return found.isStop;
    }

    public void traversal(Visitor<E> visitor) {
        traversal(visitor, TRAVERSAL_MODE_LDR);
    }

    public void traversal(Visitor<E> visitor, int traversalMode) {
        PrivateVisitor privateVisitor = new PrivateVisitor() {
            @Override
            public boolean view(TreeNode<E> treeNode) {
                return visitor.view(treeNode.value);
            }
        };
        switch (traversalMode) {
            case TRAVERSAL_MODE_DLR:
                DLRTraversal(root, privateVisitor);
                break;
            case TRAVERSAL_MODE_LDR:
                LDRTraversal(root, privateVisitor);
                break;
            case TRAVERSAL_MODE_LRD:
                LRDTraversal(root, privateVisitor);
                break;
            default:
                layerTraversal(privateVisitor);
                break;
        }
    }

    // 添加新节点之后的操作，交给子类重写
    protected void afterAdd(TreeNode<E> node) {

    }

    // 删除节点之后的操作，交给子类重写
    protected void afterRemove(TreeNode<E> node) {

    }

    // 中序遍历
    protected void LDRTraversal(TreeNode<E> cur, PrivateVisitor visitor) {
        if (visitor == null) {
            return;
        }
        if (cur == null) {
            return;
        }
        // 如果visitor停止遍历操作,则终止遍历
        if (visitor.isStop) {
            return;
        }

        LDRTraversal(cur.left, visitor);
        visitor.isStop = visitor.view(cur);
        LDRTraversal(cur.right, visitor);
    }

    // 前序遍历
    protected void DLRTraversal(TreeNode<E> cur, PrivateVisitor visitor) {
        if (visitor == null) {
            return;
        }
        if (cur == null) {
            return;
        }
        if (visitor.isStop) {
            return;
        }

        visitor.isStop = visitor.view(cur);
        DLRTraversal(cur.left, visitor);
        DLRTraversal(cur.right, visitor);
    }

    // 后序遍历
    protected void LRDTraversal(TreeNode<E> cur, PrivateVisitor visitor) {
        if (visitor == null) {
            return;
        }
        if (cur == null) {
            return;
        }
        if (visitor.isStop) {
            return;
        }

        LRDTraversal(cur.left, visitor);
        LRDTraversal(cur.right, visitor);
        visitor.isStop = visitor.view(cur);
    }

    // 层序遍历
    protected void layerTraversal(PrivateVisitor visitor) {
        if (visitor == null) {
            return;
        }
        Queue<TreeNode<E>> queue = new Queue<>();
        queue.enQueue(root);
        while (!queue.isEmpty()) {
            TreeNode<E> out = queue.deQueue();
            visitor.isStop = visitor.view(out);
            if (visitor.isStop) return;
            if (out.hasLeft()) queue.enQueue(out.left);
            if (out.hasRight()) queue.enQueue(out.right);
        }
    }

    // 前驱节点
    protected TreeNode<E> precursor(TreeNode<E> node) {
        if (!node.hasLeft()) {
            // 如果没有左子树
            TreeNode<E> cur = node.parent;
            while (cur != null) {
                int cmp = compare(node.value, cur.value);
                if (cmp > 0) return cur;
                cur = cur.parent;
            }
            return null;
        }

        // 如果有左子树
        TreeNode<E> cur = node.left;
        while (cur.right != null) {
            cur = cur.right;
        }
        return cur;
    }

    // 后驱节点
    protected TreeNode<E> successor(TreeNode<E> node) {
        if (!node.hasRight()) {
            // 没有右子树
            TreeNode<E> cur = node;
            while (cur != null) {
                int cmp = compare(node.value, cur.value);
                if (cmp < 0) return cur;
                cur = cur.parent;
            }
            return null;
        }

        // 有右子树
        TreeNode<E> cur = node.right;
        while (cur.left != null) {
            cur = cur.left;
        }
        return cur;
    }

    // 比较两个元素被
    protected int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((Comparable) e1).compareTo(e2);
    }

    // 方便以后子类重写变更节点类型
    protected TreeNode<E> createNode() {
        return new TreeNode<>();
    }

    // 使父节点连接上子节点
    protected void connectParentAndChild(TreeNode<E> parent, TreeNode<E> child, boolean isLeftChild) {
        child.parent = parent;
        if (isLeftChild) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    // 断开父节点与子节点的连接
    protected void disconnectParentAndChild(TreeNode<E> parent, TreeNode<E> child, boolean isLeftChild) {
        child.parent = null;
        if (isLeftChild) {
            parent.left = null;
        } else {
            parent.right = null;
        }
    }

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((TreeNode<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((TreeNode<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return ((TreeNode<E>) node).value;
    }

    // 内部使用的遍历接口
    protected abstract class PrivateVisitor {
        boolean isStop = false;

        public abstract boolean view(TreeNode<E> treeNode);
    }

    // 提供给外部的遍历操作接口
    public interface Visitor<E> {
        boolean view(E e);
    }

    protected class TreeNode<E> {
        public TreeNode<E> parent;
        public TreeNode<E> left;
        public TreeNode<E> right;
        public E value;

        public boolean hasChild() {
            return left != null || right != null;
        }

        public boolean hasLeft() {
            return left != null;
        }

        public boolean hasRight() {
            return right != null;
        }

        public boolean isLeftChild() {
            if (parent == null) {
                return false;
            }
            if (!parent.hasLeft()) {
                return false;
            }
            return parent.left.equals(this);
        }

        public boolean isRightChild() {
            if (parent == null) {
                return false;
            }
            if (!parent.hasRight()) {
                return false;
            }
            return parent.right.equals(this);
        }
    }
}
