package crawler;

public class Params {
    public static final int TIMEOUT_IF_QUEUE_IS_EMPTY = 20;
    public static final int TIMEOUT_IF_ANALYZEDB_IS_FULL = 10;
    public static final int THREADS_COUNT = 4;
    public static final int PAGE_QUEUE_SIZE = 10;
    public static final int URL_QUEUE_SIZE = 100;

    public static final String URL_SEARCH_STRING1 = "a[href]";
    public static final String URL_SEARCH_STRING2 = "abs:href";
    public static final String CONNECTION_USERAGENT_STRING = "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:59.0) Gecko/20100101";
    public static final String STARTING_URL = "https://sitejs.org/";
    public static final int AWAIT_TERMINATION_TIMEOUT_IN_SECONDS = 60;
    public static final int SHUTDOWN_TIMEOUT_IN_SECONDS = 20;
    public static final String LOG_FILE_NAME = "MyLogFile.log";
    public static final String LOG_FILE_PATH = System.getProperty("user.dir") + "/";
}
