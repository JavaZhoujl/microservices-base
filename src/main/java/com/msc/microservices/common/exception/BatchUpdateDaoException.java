package com.msc.microservices.common.exception;

/**
 * 批量操作dao运行时异常
 *
 * @author zjl
 */
public class BatchUpdateDaoException extends RuntimeException {
    /**
     * 唯一序列号
     */
    private static final long serialVersionUID = 1505628555656145469L;
    /**
     * 批量大小
     */
    private final int batchSize;
    /**
     * 参数数量
     */
    private final int parameterSize;
    /**
     * 当前遍历参数列表下标位置
     */
    private final int curPosition;
    /**
     * 批量操作已影响行数二维数组
     */
    private final int[][] rowsAffectedArr;
    /**
     * 批量操作总影响行数
     */
    private long rowsAffected;

    public BatchUpdateDaoException(String message, int batchSize, int parameterSize,
                                   int curPosition, int[][] rowsAffectedArr, Throwable th) {
        super(message, th);
        this.batchSize = batchSize;
        this.parameterSize = parameterSize;
        this.curPosition = curPosition;
        this.rowsAffectedArr = rowsAffectedArr;
        for (int[] arr : this.rowsAffectedArr) {
            for (int row : arr) {
                rowsAffected += row;
            }
        }
    }

    public int getBatchSize() {
        return batchSize;
    }

    public int getParameterSize() {
        return parameterSize;
    }

    public int getCurPosition() {
        return curPosition;
    }

    public int[][] getRowsAffectedArr() {
        return rowsAffectedArr;
    }

    public long getRowsAffected() {
        return rowsAffected;
    }

    @Override
    public String toString() {
        return String.format("%s==>参数总数:%d批量大小:%d当前下标:%d影响行数:%d", getMessage(), parameterSize,
                batchSize, curPosition, rowsAffected);
    }
}
