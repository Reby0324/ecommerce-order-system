# Java Maven 電商訂單管理系統

本專案是一個使用 **Java + Maven + Swing + MySQL** 製作的桌面版電商訂單管理系統，主要練習 Java 物件導向、JDBC 資料庫連線、DAO / Service 分層架構，以及 Swing 圖形化介面設計。

## 專案功能

- 會員登入 / 註冊
- 商品資料新增、查詢、修改、刪除
- 訂單資料新增與查詢
- MySQL 資料庫連線
- Swing 視窗化操作介面
- Maven 管理專案與套件

## 使用技術

| 技術 | 說明 |
|---|---|
| Java | 主要開發語言 |
| Maven | 專案建置與套件管理 |
| Swing | Java 桌面 GUI 介面 |
| MySQL | 資料庫 |
| JDBC | Java 連接 MySQL |
| Eclipse | 開發工具 |
| Git / GitHub | 版本控制與專案展示 |

## 專案結構

```text
src/main/java
├─ controller   # Swing UI 畫面控制
├─ dao          # 資料庫存取介面
├─ dao.impl     # DAO 實作類別
├─ entity       # 實體類別 / Model
├─ service      # 商業邏輯介面
├─ service.impl # Service 實作類別
└─ util         # 工具類別，例如資料庫連線
```

## 資料庫設定

本專案使用 MySQL，執行前請先建立資料庫。

資料庫名稱範例：

```sql
CREATE DATABASE ecommerce_order_system;
```

請確認資料庫連線設定與你的 MySQL 帳號密碼一致，例如：

```java
String url = "jdbc:mysql://localhost:3306/ecommerce_order_system";
String user = "root";
String password = "你的MySQL密碼";
```

> 注意：上傳 GitHub 時，不建議把真正的資料庫密碼公開。

## 如何執行專案

1. 下載或 clone 此專案

```bash
git clone https://github.com/你的GitHub帳號/你的Repository名稱.git
```

2. 使用 Eclipse 匯入 Maven 專案

```text
File → Import → Maven → Existing Maven Projects
```

3. 選擇專案資料夾，確認 `pom.xml` 有被勾選
4. 建立 MySQL 資料庫與資料表
5. 修改資料庫連線帳號密碼
6. 執行有 `main()` 方法的 UI 類別

## 打包成 JAR

在 Eclipse 專案上按右鍵：

```text
Run As → Maven build...
```

Goals 輸入：

```text
clean package
```

打包完成後，JAR 檔會出現在：

```text
target/
```

## 專案目的

本專案是 Java 全端學習過程中的練習作品，主要目標是熟悉：

- Java 物件導向程式設計
- Swing GUI 介面開發
- JDBC 與 MySQL 資料庫操作
- Maven 專案管理
- GitHub 專案版本管理

## 作者

彭筠琋

## 授權

此專案僅作為學習與作品展示用途。
