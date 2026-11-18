package ru.kykhalova.structures;
import java.util.ArrayList;

public class Node {
    private int value; // значение узла
    private boolean hasValue; // задано ли значение
    private Node parent;
    private Node left;
    private Node right;

    public Node() { // конструктор без аргументов
        this.hasValue = false;
    }

    public void add(int newValue) {
        if (!hasValue) {
            this.value = newValue;
            this.hasValue = true;
            return;
        }
        if (newValue > this.value) {
            if (right == null) {
                right = new Node();
                right.parent = this;
            }
            right.add(newValue);
        }
        else {
            if (left == null) {
                left = new Node();
                left.parent = this;
            }
            left.add(newValue);
        }
    }


    public boolean contains(int searchValue) {
        if (!hasValue) return false;
        if (this.value == searchValue) return true;

        if (searchValue > this.value) {
            return right != null && right.contains(searchValue);
        }
        else {
            return left != null && left.contains(searchValue);
        }
    }

    // удаление значения с сохранением потомков
    public void remove(int target) {
        if (!hasValue) return;

        if (this.value == target) {
            ArrayList<Integer> savedChildren = new ArrayList<>(); // собираем всех потомков этого узла в список
            collectChildren(left, savedChildren);
            collectChildren(right, savedChildren);

            if (parent == null) {  // если удаляем самый верхний узел (корень)
                hasValue = false;
                left = null;
                right = null;
                for (int val : savedChildren) {
                    this.add(val);
                }
            }
            else { // отцепляем узел от родителя
                if (parent.left == this) parent.left = null;
                if (parent.right == this) parent.right = null;

                Node root = this;
                while (root.parent != null) {
                    root = root.parent;
                }
                for (int val : savedChildren) {
                    root.add(val);
                }
            }
        }
        else if (target > this.value && right != null) {
            right.remove(target);
        }
        else if (target <= this.value && left != null) {
            left.remove(target);
        }
    }

    // вспомогательный метод для сбора значений потомков
    private void collectChildren(Node node, ArrayList<Integer> list) {
        if (node == null || !node.hasValue) return;
        list.add(node.value);
        collectChildren(node.left, list);
        collectChildren(node.right, list);
    }

    // приведение к строке (левосторонний обход в глубину)
    @Override
    public String toString() {
        if (!hasValue) return "";
        String result = "";
        if (left != null) {
            result += left.toString() + " ";
        }
        result += value;
        if (right != null) {
            result += " " + right.toString();
        }
        return result.trim();
    }
}