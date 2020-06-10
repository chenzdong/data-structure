package tree;

/**
 * @author: czd
 * @create: 2020-06-10 10:43
 */
public class BinarySearchTree {
    public TreeNode find(TreeNode node, int val) {
        while (node != null) {
            if (node.data == val) {
                return node;
            } else if (node.data > val) {
                node = node.left;
            } else {
                node = node.right;
            }
        }
        return null;
    }
    public void insert(TreeNode node, int data) {
        if (node == null) {
            node = new TreeNode(data);
            return;
        }
        while (node != null) {
            if (data > node.data) {
                if (node.right == null) {
                    node.right = new TreeNode(data);
                    return;
                }
                node = node.right;
            } else {
                if (node.left == null) {
                    node.left = new TreeNode(data);
                    return;
                }
                node = node.left;
            }
        }
    }
    public void delete(TreeNode node, int data) {
        /**
         * 1. 没有子节点，直接删除：父节点指向空
         * 2. 有一个子节点，子节点替换待删除节点
         * 3. 有两个子节点，右子树的最小值替换待删除节点，然后删除掉改最小节点（该节点肯定没有左节点，只有一个右节点或者没有子节点）
         */

        TreeNode p = node;
        // p的父节点
        TreeNode pp = null;
        // 找到待删除的节点
        while (p != null && p.data != data) {
            pp = p;
            if (data > p.data) {
                p = p.right;
            } else {
                p = p.left;
            }
        }
        // 没有对应的节点
        if(p == null) {
            return;
        }
        // 有两个子节点
        if (p.left != null && p.right != null) {
            TreeNode min = p.right;
            TreeNode minp = p;
            while (p.left != null) {
                minp = min;
                min = p.left;
            }
            // 替换待删除的节点
            p.data = min.data;
            // 删除最小的节点(一个或0个子节点)
            p = min;
            pp = minp;
        }
        TreeNode child = null;
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        } else {
            child = null;
        }
        // 父节点
        if (pp == null) {
            node = child;
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }
    }

}
