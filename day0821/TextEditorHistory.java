package day0821;

import java.util.ArrayDeque;
import java.util.Deque;

public class TextEditorHistory {
    private Deque<String> undoStack = new ArrayDeque<>();
    private Deque<String> redoStack = new ArrayDeque<>();

    public void type(String text) {
        undoStack.push(text);
        redoStack.clear(); 
        System.out.println("輸入: \"" + text + "\"");
        printStatus();
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("【Undo 失敗】Undo stack 為空，無操作可復原");
            printStatus();
            return;
        }
        String action = undoStack.pop();
        redoStack.push(action);
        System.out.println("執行 Undo: 復原 \"" + action + "\"");
        printStatus();
    }
    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("【Redo 失敗】Redo stack 為空，無操作可重做");
            printStatus();
            return;
        }
        String action = redoStack.pop();
        undoStack.push(action);
        System.out.println("執行 Redo: 重做 \"" + action + "\"");
        printStatus();
    }

    private void printStatus() {
        System.out.println("  Undo Stack: " + undoStack);
        System.out.println("  Redo Stack: " + redoStack);
        System.out.println("----------------------------------------");
    }

    public static void main(String[] args) {
        TextEditorHistory editor = new TextEditorHistory();

        editor.undo();
        editor.redo();

        editor.type("Hello");
        editor.type("World");
        editor.undo();
        editor.redo();
        editor.type("Java"); 
        editor.undo();
    }
}
