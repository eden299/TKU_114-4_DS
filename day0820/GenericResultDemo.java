package day0820;

class Result<T> {
    private boolean success;
    private String message;
    private T data;

    public Result(boolean success, String message, T data) {
        this.success = success;
        this.message = message;
        this.data = success ? data : null;
    }

    public static <T> Result<T> ok(T data, String message) {
        return new Result<>(true, message, data);
    }

    public static <T> Result<T> fail(String message) {
        return new Result<>(false, message, null);
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public T getData() { return data; }
}

public class GenericResultDemo {
    public static void main(String[] args) {
        Result<String> strSuccess = Result.ok("Hello Java", "操作成功");
        Result<String> strFail = Result.fail("找不到資料");

        Result<Integer> intSuccess = Result.ok(200, "計算完成");
        Result<Integer> intFail = Result.fail("除數不能為零");

        String strData = strSuccess.getData();
        Integer intData = intSuccess.getData();

        System.out.println("String Result: " + strData + " | Fail Data: " + strFail.getData());
        System.out.println("Integer Result: " + intData + " | Fail Data: " + intFail.getData());
    }
}
