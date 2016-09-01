package postme.tacademy.com.postme.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Monkey on 2016. 8. 31..
 */
public class DBHelper extends SQLiteOpenHelper {


    // DBHelper 생성자로 관리할 DB 이름과 버전 정보를 받음
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // DB를 새로 생성할 때 호출되는 함수
    @Override
    public void onCreate(SQLiteDatabase db) {
        // 새로운 테이블 생성
        /* 이름은 POSTMEBOOK, 자동으로 값이 증가하는 _id 정수형 기본키 컬럼과
        content 문자열 컬럼, create_at 문자열 컬럼으로 구성된 테이블을 생성. */
        db.execSQL("CREATE TABLE POSTMEBOOK (push_id INTEGER PRIMARY KEY AUTOINCREMENT, content TEXT, create_date TEXT);");
    }

    // DB 업그레이드를 위해 버전이 변경될 때 호출되는 함수
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void forinsert(){
        for (int i=0; i<5; i++){
            insert("2016-09-01","내용"+i);
        }
    }
    public void insert(String create_at, String content) {
        // 읽고 쓰기가 가능하게 DB 열기
        SQLiteDatabase db = getWritableDatabase();
        // DB에 입력한 값으로 행 추가
        db.execSQL("INSERT INTO POSTMEBOOK VALUES(null, '" + content + "', '" + create_at + "');");
        db.close();
    }

    public void delete(String content) {
        SQLiteDatabase db = getWritableDatabase();
        // 입력한 항목과 일치하는 행 삭제
        db.execSQL("DELETE FROM POSTMEBOOK WHERE content='" + content + "';");
        db.close();
    }

    public ArrayList<DBinfo> getResult() {
        // 읽기가 가능하게 DB 열기
        SQLiteDatabase db = getReadableDatabase();
        String result = "";
        ArrayList arrayList = new ArrayList<DBinfo>();
        // DB에 있는 데이터를 쉽게 처리하기 위해 Cursor를 사용하여 테이블에 있는 모든 데이터 출력
        Cursor cursor = db.rawQuery("SELECT * FROM POSTMEBOOK", null);
        while (cursor.moveToNext()) {
             DBinfo dbinfo = new DBinfo();
            dbinfo.setPush_id(cursor.getInt(0));
            dbinfo.setContent(cursor.getString(1));
            dbinfo.setCreate_date(cursor.getString(2));
            arrayList.add(dbinfo);
        }

        return arrayList;
    }
}