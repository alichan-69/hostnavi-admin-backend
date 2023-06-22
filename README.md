# hostnavi-admin-backend

<img src="./readme/Logo.png">

`hostnavi-admin-backend`は、貸し出し管理システムのサンプルアプリケーション`HostNavi`のバックエンドのリポジトリです。<br>
`HostNavi` のバックエンドのアーキテクチャ・使用されている技術・開発方法の詳細な情報を記載しています。
<br>

# API 一覧

API は REST で設計されています。<br>
以下 API の一覧です。

| API                                      | 機能                                                                                 |
| ---------------------------------------- | ------------------------------------------------------------------------------------ |
| 宿泊施設 API                             | 宿泊施設の CRUD 処理ができる API                                                     |
| 宿泊施設のアメニティ API                 | 宿泊施設のアメニティの CRUD 処理ができる API                                         |
| 宿泊施設に設置されている施設の API       | 宿泊施設に設置されている施設が取得できる API                                         |
| 宿泊施設のビュー(掲載ページ閲覧数)の API | 宿泊施設のビュー(掲載ページ閲覧数)の CRUD 処理ができる API                           |
| 予約 API                                 | 予約の CRUD 処理ができる API                                                         |
| 予約メッセージ API                       | 予約メッセージの CRUD 処理ができる API(WebScoket 通信でのメメッセージの送受信も含む) |
| 予約レビュー API                         | 予約レビューの CRUD 処理ができる API                                                 |
| ユーザー API                             | ユーザーの CRUD 処理ができる API                                                     |
| クレジットカード API                     | クレジットカードの CRUD 処理ができる API                                             |

# ディレクトリ構成

```lua
├── pom.xml -- Mavenでのライブラリの依存関係が記載されているXMLファイル
├── src/main/java -- アプリケーションに関連するクラスを格納するディレクトリ
│   └── com.alichan.hostnavi.admin -- アプリケーションに関連するクラスを格納するパッケージ
│　　　　├── HostNaviAdminApplication.java -- SpringBootアプリケーションを起動するクラス
│　　　　├── application -- アプリケーション層に関連するクラスを格納するパッケージ
│　　　　│   ├── auth -- 認証関連のクラスを格納するパッケージ
│　　　　│   │   ├── AuthUser.java -- 認証ユーザーのDTOを定義するクラス
│　　　　│   │   ├── AuthUserService.java -- 認証の際に使用する処理を定義するクラス
│　　　　│   │   ├── JWTAuthenticationFilter.java -- WebSecurityConfigクラスで使用する認証フィルターを定義するクラス
│　　　　│   │   ├── JWTAuthorizationFilter.java -- WebSecurityConfigクラスで使用する認可フィルターを定義するクラス
│　　　　│   │   └── WebSecurityConfig.java -- 各APIへのアクセス制限をかけたり、認証・認可フィルターを設定するConfigクラス
│　　　　│   ├── controller -- コントローラークラスを格納するパッケージ
│　　　　│   │   └── InnInnController.java -- コントローラークラス
│　　　　│   ├── form -- アプリケーション層からドメイン層に渡すformを格納するパッケージ
│　　　　│   │   └── FormattedReservationReservationUrlParam.java -- アプリケーション層からドメイン層に渡すform
│　　　　│   ├── response -- APIのレスポンスに関するクラスを格納するパッケージ
│　　　　│   │   ├── Response.java -- 各HTTPステータスごとに返すレスポンスを分け、呼び出すメソッドの一覧を格納したクラス
│　　　　│   │   └── ResponseMessageEnums.java -- 各HTTPステータスにごとに返すメッセージの一覧を格納したenum
│　　　　│   ├── util -- アプリケーション層で利用する便利関数をまとめたクラスを格納するパッケージ
│　　　　│   │   └── Page.java -- アプリケーション層で利用する便利関数をまとめたクラス
│　　　　│   └── validation -- リクエストパラメータにかけるバリデーション関連のクラスをまとめたパッケージ
│　　　　│       ├── Util.java -- リクエストパラメータにかけるバリデーションで利用する便利関数をまとめたクラス
│　　　　│       ├── annotation -- リクエストパラメータにかけるバリデーション用のアノテーションを定義するクラスを格納したパッケージ
│　　　　│       │   └── StringType.java -- リクエストパラメータにかけるバリデーション用のアノテーションを定義するクラス
│　　　　│       └── validator -- リクエストパラメータにかけるバリデーション用のアノテーションを定義するクラスで使用するバリデーションの関数を定義するクラスを格納したパッケージ
│　　　　│           └── StringTypeValidator.java -- リクエストパラメータにかけるバリデーション用のアノテーションを定義するクラスで使用するバリデーションの関数を定義するクラスを格納したパッケージ
│　　　　├── config -- Configクラスを格納するパッケージ
│　　　　│   ├── MyBatisConfig.java
│　　　　│   ├── SwaggerConfig.java
│　　　　│   ├── WebMvcConfig.java
│　　　　│   └── WebSocketConfig.java
│　　　　├── domain -- ドメイン層のクラスを格納するパッケージ
│　　　　│   ├── logic -- サービスクラスで共通して使用される処理を含む関数を定義したクラスを格納するパッケージ
│　　　　│   │   └── InnInnLogic.java -- サービスクラスで共通して使用される処理を含む関数を定義したクラス
│　　　　│   └── service -- ビジネスロジック（ユーザーインターフェースとのやり取り（ルーティング・フォームのバリデーション等）またはデータベースとの直接のやり取り以外の処理）を含む関数を定義したクラスを格納するパッケージ
│　　　　│       └── InnInnService.java -- ビジネスロジック（ユーザーインターフェースとのやり取り（ルーティング・フォームのバリデーション等）またはデータベースとの直接のやり取り以外の処理）を含む関数を定義したクラス
│　　　　├── dto -- アプリケーション層とドメイン層の間でやり取りするデータをつめるクラスを格納するパッケージ
│　　　　│   ├── requestparam -- アプリケーション層からドメイン層に降りてくるAPIのリクエストパラメータをつめるクラスを格納するパッケージ
│　　　　│   │   └── InnInnRequestParam.java -- アプリケーション層からドメイン層に降りてくるAPIのリクエストパラメータをつめるクラス
│　　　　│   └── responsedata -- ドメイン層からアプリケーション層に上げるAPIのレスポンスパラメータをつめるクラスを格納するパッケージ
│　　　　│       └── InnInnResponseData.java-- ドメイン層からアプリケーション層に上げるAPIのレスポンスパラメータをつめるクラス
│　　　　├── error -- エラー関連のクラスを格納するパッケージ
│　　　　│   ├── Assert.java -- HTTPステータスに基づいて作成された各エラーを発生させる関数
│　　　　│   ├── ErrorControllerImplement.java -- 設定されたURL以外のURLにアクセスした際にエラーレスポンスを返す様に設定した関数を定義したクラス
│　　　　│   ├── GlobalExceptionHandler.java -- 発生したエラーに基づいてエラーレスポンスを返す様に設定したクラス
│　　　　│   └── exception -- HTTPステータスに基づいて作成したエラーのクラスを格納するパッケージ
│　　　　│       └── UnauthorizedException.java -- HTTPステータスに基づいて作成したエラーのクラス
│　　　　├── infrastracture -- インフラストラクチャ層に関連するクラスを格納するパッケージ
│　　　　│   ├── Generator.java -- MyBatisGeneratorを起動するためのクラス
│　　　　│   ├── mapper -- MyBatisでSQLを定義したxmlとSQLを実行するメソッドを紐づけるクラスを格納するパッケージ
│　　　　│   │   ├── custom -- 自分で作成したmapperクラスを格納するパッケージ
│　　　　│   │   │   └── InnInnCustomMapper.java -- 自分で作成したmapperクラス
│　　　　│   │   └── generated -- MyBatisGeneratorで作成されたmapperクラスを格納するパッケージ
│　　　　│   │       └── InnInnMapper.java -- MyBatisGeneratorで作成されたmapperクラス
│　　　　│   └── model -- SQLから帰ってきた結果をつめるクラスを格納するパッケージ
│　　　　│       ├── custom -- 自分で作成したmodelクラスを格納するパッケージ
│　　　　│       │   └── InnInnCustom.java -- 自分で作成したmodelクラス
│　　　　│       └── generated -- MyBatisGeneratorで作成されたmodelクラスを格納するパッケージ
│　　　　│           ├── InnInn.java -- MyBatisGeneratorで作成されたmodelクラス
│　　　　│           └── InnInnExample.java -- MyBatisGeneratorで作成されたSQLでの検索条件を設定するためのクラス
│　　　　└── util -- 層間で共通して使用される便利関数を格納するクラスを格納するパッケージ
│　　　　    └── ModelMapperUtil.java -- 層間で共通して使用される便利関数を格納するクラス
├── src/main/resources
│            ├── META-INF -- アプリケーションの動作に必要な設定ファイルを格納するディレクトリ
│            │   └── additional-spring-configuration-metadata.json -- application.yamlに設定する追加の環境変数を記載するファイル
│            ├── application.yml -- アプリケーションの動作に必要な変数(データベースとの接続情報など)や環境変数を設定するファイル
│            ├── com/alichan/hostnavi/admin -- アプリケーションで動作のために使用されるファイルを格納するディレクトリ
│            │   ├── infrastracture -- インフラストラクチャ層に関連するファイルを格納するディレクトリ
│            │   │   └── mapper -- オブジェクトに紐づけるSQLを設定したファイルを格納するディレクトリ
│            │   │       ├── custom -- 自分で作成したオブジェクトに紐づけるSQLを設定したファイルを格納するディレクトリ
│            │   │       │        └── InnInnCustomMapper.xml -- 自分で作成したオブジェクトに紐づけるSQLを設定したファイル
│            │   │       └── generated -- MyBatisGeneratorで作成されたオブジェクトに紐づけるSQLを設定したファイルを格納するディレクトリ
│            │   │           └── InnInnMapper.xml -- MyBatisGeneratorで作成されたオブジェクトに紐づけるSQLを設定したファイル
│            │   └── minio -- minioに関連するファイルを格納するディレクトリ
│            │       └── policy.json -- バケットに設定するポリシーを設定したjsonファイル
│            ├── generator.properties -- MyBatisGenerator実行時に必要な変数を記載するファイル
│            └── generatorConfig.xml -- MyBatisGenerator実行時に必要な設定を記載するファイル
├── src/test/java -- テスト関連のコードを格納するファイル
│   └── com.alichan.hostnavi.admin -- テスト関連のコードを格納するパッケージ
│       ├── CsvDataSetLoader.java -- csvファイルでテスト前後のデータベースのデータを挿入、確認できるようにデータをセットするクラスを書き換えたクラス
│       ├── Env.java -- テストで使用する環境変数を格納するクラス（データベースの接続情報等）
│       ├── InnInnControllerTest.java -- 各エンドポイントごとにテストクラスを作成してテストコードを記載するクラス
│       └── Util.java -- テストコードを書く際に使用する便利関数を格納したクラス
└── src/test/resources
    └── com.alichan.hostnavi.admin
        └── innInn -- 各APIのエンドポイントごとにテストのリソースを格納するディレクトリ
            ├── create -- データを登録するAPIをテストする際に使用するリソースを格納するディレクトリ
            │   └── test1 -- テストケース1をテストする際に使用するリソースを格納するディレクトリ
            ├── delete  │ -- データを削除するAPIをテストする際に使用するリソースを格納するディレクトリ
            ├── update  │ -- データを更新するAPIをテストする際に使用するリソースを格納するディレクトリ
            └── get     │ -- データを取得するAPIをテストする際に使用するリソースを格納するディレクトリ
                        ├── database -- データベースに関連するテストのリソースを格納するディレクトリ
                        │   ├── setUp -- テスト前にテープルにデータを登録するために使用するcsvを格納するディレクトリ
                        │   │   ├── inn_inn.csv -- テスト前にテープルにデータを登録するために使用するcsv
                        │   │   ├── table-ordering.txt -- テスト前にテープルにデータを登録する順番を定義するファイル
                        │   │   ├── user_credit_card.csv
                        │   │   └── user_user.csv
                        │   └── expected -- テスト後にテープルのデータを確認するために使用するcsvを格納するディレクトリ
                        │       ├── inn_inn.csv -- テスト後にテープルのデータを確認するために使用するcsv
                        │       ├── inn_inn_amenity_relation.csv
                        │       ├── inn_inn_facility_relation.csv
                        │       └── table-ordering.txt-- テスト後にテープルのデータを確認する順番を定義するファイル
                        ├── requestParam.json -- テスト時にAPIの入力として使用するjson
                        └── responseData.json -- テスト後にAPIの出力として確認されるjson
```

# 採用技術一覧

メインで使用している技術と、その選定理由を記載しています。<br>

| 技術                          | 選定理由                                                                                                                                                                                                                                   |
| ----------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ |
| Java17                        | 現在多くの企業で使用される静的型付け言語であり、安全性の高いコードが書けると思ったため。                                                                                                                                                   |
| SpringBoot2.7                 | Java のフレームワークとして最も有名なフレームワークであり、認証やテスト、WebSocket 通信等サービスを作る際に必要な機能を実装できるライブラリを一通り提供しているため。                                                                      |
| SpringSecurity5.7             | SpringBoot の提供している認証用ライブラリであり、使用すれば簡単に認証が実装できると思ったため。また、今後外部サービスによる認証を組み込むことになった時、SpringSecurity を使用して認証を実装しておけば、簡単に組み込めそうだと思ったため。 |
| Java-JWT4.3                   | Cookie にユーザーのメールアドレスやパスワードを保持したくなかったので、token での認証を行いたかったため。SpringSecurity に組み込むことが容易だったため。                                                                                   |
| MyBatis3.0                    | OR マッパーではあるが、SQL を自分で直接書いてメソッドやモデルに紐づけることが容易であり、今後パフォーマンスチューニングを行う際に柔軟に SQL を組みやすいと思ったため。                                                                     |
| PageHelper                    | MyBatis でのページングを簡単に実装できるため。                                                                                                                                                                                             |
| MyBatisGenerator              | 基本的な CRUD 処理を行う SQL 実行用メソッドや返されるモデル等を自動で生成してくれるため。自分で組そういったメソッドやモデルを作成する手間が減るため。                                                                                      |
| ModelMapper                   | 層間のデータのやり取りを行う際に何度もオブジェクトの詰め替え作業を行うことが予想され、その手間を軽減したかったため。                                                                                                                       |
| spring-boot-starter-websocket | SpringBoot の提供している WebSocket 通信用ライブラリであり、使用すれば簡単に WebSocket 通信が実装できると思ったため。                                                                                                                      |
| Lombok                        | Java で書く基本的なコードをアノテーションのみで省略してくれ、コードを書く手間が減らせると思ったため。                                                                                                                                      |
| Springfox                     | API の仕様書をコードから自動生成してくれるため。仕様書を書く手間が減らせると思ったため。                                                                                                                                                   |
| DBUnit                        | データベースと連携して行うテストで、csv でのテスト前のデータ挿入、テスト後のデータ確認が簡単に実装できると思われたため。                                                                                                                   |

# 開発ガイドライン

パッケージ構成のルール、テストの実施方法、環境変数の設定方法、ライブラリの使用方法等の開発の際に必要となる情報を記載しています。<br>

## パッケージ構成のルール

パッケージは以下の方針で構成されています。<br>
クラスや設定ファイルをどのパッケージに入れるか以下ルールから判断し、格納します。

- 3 層アーキテクチャを採用。各層をアプリケーション層、ドメイン層、インフラストラクチャ層と名づけ、紐づくパッケージを作成。各パッケージに関連するパッケージやクラスを格納する。3 層それぞれの定義は以下。
  - アプリケーション層：API の受け取り部分を担当。ビジネスロジックは書かない。具体的に格納するクラスとしては、入力のバリデーション用クラス、認証関連のクラス、ルーティングを行うコントローラークラス等。
  - ドメイン層：ビジネスロジック(アプリケーション層で行われる処理、またはデータベースとの直接のやり取り以外の処理）を担当。具体的に格納するクラスとしては、ビジネスロジックを定義した関数を格納したサービスクラス等。
  - インフラストラクチャ層：データベースとの直接のやり取りを担当。具体的に格納するクラスとしては、SQL 実行後に実行結果が格納されるモデルクラス、SQL とメソッドの紐付けを行うマッパーインターフェース等。
- 各層にまたがるパッケージは層のパッケージに入れず、外でパッケージを作成。
- 層の中で関心ごとにパッケージを作成し、クラスを保存。
- 具体的なディレクトリ構成の例は以下。

```lua
├── application -- アプリケーション層のクラスを格納するパッケージ
│   ├── auth -- 関心ごとにパッケージを作成して、関連するクラスを格納
│   └── validation
├── domain -- ドメイン層のクラスを格納するパッケージ
├── infrastracture -- インフラストラクチャ層のクラスを格納するパッケージ
└── dto -- 層にまたがって使用されるパッケージは層の中に入れず、外部にパッケージを作成
```

## API 仕様書の作成方法

API 仕様書は Springfox を用いて自動生成しています。<br>
Springfox では自動生成された API 仕様書では API の仕様の閲覧、実行ができます。<br>
以下の流れで API 仕様書を作成、閲覧できます。

1. コントローラークラスにアノテーションを付与し、API の説明を記載

```Java
// 1. エンドポイントに対応するAPIの大項目名、説明を記載
@Api(tags = "宿泊施設API", description = "宿泊施設のCRUD処理ができるAPI")
@Controller
// 2. 認証が必要なAPI等、リクエスト時にヘッダーを付与する必要のあるAPIは実行時ヘッダーに値を設定できる様に設定。
@ApiImplicitParams({@ApiImplicitParam(name = "X-Auth-Token", value = "認証用のtoken", required = true,
    dataType = "string", paramType = "header")})
@RequestMapping("/inns")
public class InnInnController {
  @Autowired
  private InnInnService innInnService;

  @GetMapping(path = "/all")
  // 3. 各APIの説明を記載
  @ApiOperation(value = "全ての宿泊施設のリストを取得")
  @ResponseBody
  public Response<List<InnInnResponseData>> getAllInn(
    // 4. URLパラメーターを設定する場合、パラメーターの説明を記載
    @ApiParam(value = "宿泊施設の所有者のid",
      example = "1") @RequestParam(value = "owner-id", required = false) Long ownerId) {
    return Response.success(innInnService.getAllInn(ownerId));
  }
}
```

2. アプリケーションサーバーを立ち上げ、`http://localhost:8080/swagger-ui`にアクセス。以下ページが立ち上がる。

<img src="./readme/api1.png">

3. 以下の様に API の説明を開くことで、API の仕様の閲覧・実行ができる。

<img src="./readme/api2.png">

## 認証の実装方法

認証はメールアドレスとパスワードでの認証を行なっています。<br>
認証成功後は token が発行され、その token をクライアントが Cookie で保持することで都度メールアドレスとパスワードを入力して認証を行う必要性をなくしています。<br>
実装方法は以下です。

1. `WebSecurityConfig`クラスで認証の設定を行う。

```Java
package com.alichan.hostnavi.admin.application.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //1. csrfの無効化を行う
    http.csrf().ignoringAntMatchers("/**");
    // 2. OPTIONSメソッドへのCORSの無効化を行う。APIリクエスト時にプリフライトリクエストが行われるAPIがあったため。
    http.cors();
    // 3. 認証が必要なAPIのエンドポイントを設定。
    http.authorizeHttpRequests(auth -> {
      auth.antMatchers("/users/login").permitAll();
      auth.antMatchers(HttpMethod.POST, "/users").permitAll();
      auth.antMatchers(HttpMethod.PUT, "/users/{\\d+}").permitAll();
      auth.antMatchers(HttpMethod.POST, "/users/image/{\\d+}").permitAll();
      auth.antMatchers("/credit-cards/**").authenticated();
      auth.antMatchers("/reservations/**").authenticated();
      auth.antMatchers("/reviews/**").authenticated();
      auth.antMatchers("/messages/**").authenticated();
      auth.antMatchers("/inns/**").authenticated();
      auth.antMatchers("/faclities/**").authenticated();
      auth.antMatchers("/amenities/**").authenticated();
      auth.antMatchers("/views/**").authenticated();
      auth.antMatchers(HttpMethod.DELETE, "/users/{\\d+}").authenticated();
    });
    // 4. 認証Filterを設定
    http.addFilter(new JWTAuthenticationFilter(
        authenticationManager(http.getSharedObject(AuthenticationConfiguration.class))));
    // 5. 認可Filterを設定
    http.addFilterAfter(new JWTAuthorizationFilter(), JWTAuthenticationFilter.class);
    return http.build();
  }

  // 6. データベースへの保存時にパスワードをハッシュ化する様に設定
  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }

  // 7. 認証で使用するauthenticationManagerを設定する処理
  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration) throws Exception {
    return authenticationConfiguration.getAuthenticationManager();
  }
}
```

2. `JWTAuthenticationFilter`クラスで、JWT 認証用の認証クラスを定義する。

```Java
package com.alichan.hostnavi.admin.application.auth;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.alichan.hostnavi.admin.application.response.ResponseMessageEnums;
import com.alichan.hostnavi.admin.dto.requestparam.UserUserRequestParam;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
  private AuthenticationManager authenticationManager;

  public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
    // 1. ログインパスのを指定する
    setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/auth/login", "POST"));

    // 2. ログイン成功時にtokenを発行してレスポンスヘッダーにセットする
    this.setAuthenticationSuccessHandler((request, response, chain) -> {
      AuthUser authUser = (AuthUser) chain.getPrincipal();
      // 3. JWTトークンの作成を行う
      String token = JWT.create().withIssuer("alichan").withClaim("id", authUser.getId())
          .sign(Algorithm.HMAC256("secret"));
      // 4. ヘッダーにX-AUTH-TOKENというキーで生成したトークンを付与する
      response.setHeader("X-AUTH-TOKEN", token);
      response.setHeader("Access-Control-Expose-Headers", "X-AUTH-TOKEN");
      response.setStatus(HttpServletResponse.SC_OK);
      // 5. レスポンスの設定を行う。
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      response.getWriter().write("{\n" + "    \"message\": \""
          + ResponseMessageEnums.SUCCESS.getMessage() + "\",\n" + "    \"data\": null\n" + "}");
    });

    // 6. ログイン失敗時の処理を書く
    this.setAuthenticationFailureHandler((request, response, chain) -> {
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      response.setContentType("application/json");
      response.setCharacterEncoding("UTF-8");
      // 7. レスポンスの設定を行う。
      response.getWriter()
          .write("{\n" + "    \"message\": \"" + ResponseMessageEnums.UNAUTHORIZED.getMessage()
              + "\",\n" + "    \"data\": null\n" + "}");
    });
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest request,
      HttpServletResponse response) throws AuthenticationException {
    UserUserRequestParam userRequestParam = null;
    try {
      // 8. リクエストされたユーザーを読み取る
      userRequestParam =
          new ObjectMapper().readValue(request.getInputStream(), UserUserRequestParam.class);

      // 9. 読み取ったユーザーで認証を行う
      return this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
          userRequestParam.getMail(), userRequestParam.getPassword()));
    } catch (IOException error) {
      throw new RuntimeException(error);
    }
  }
}

```

3. `JWTAuthorizationFileter`クラスで認可用の認可クラスを作成する。

```Java
package com.alichan.hostnavi.admin.application.auth;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

public class JWTAuthorizationFilter extends OncePerRequestFilter {
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    // 1. ヘッダーのキーを指定して値(トークン)を取得する
    String value = request.getHeader("X-AUTH-TOKEN");
    if (value == null || !value.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }
    String token = value.substring(7);
    // 2. tokenの検証と認証を行う
    DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("secret")).build().verify(token);
    // 3. メールアドレスの取得を行う
    String mail = decodedJWT.getClaim("mail").toString();
    // 4. ログイン状態の設定を行う
    SecurityContextHolder.getContext()
        .setAuthentication(new UsernamePasswordAuthenticationToken(mail, null, new ArrayList<>()));
    filterChain.doFilter(request, response);
  }
}

```

4. `AuthUserService`クラスでデータベースに接続してメールアドレスでユーザーを検索、認証されたユーザーの情報を返す。

```Java
package com.alichan.hostnavi.admin.application.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.UserUserMapper;
import com.alichan.hostnavi.admin.infrastracture.model.generated.UserUser;
import com.alichan.hostnavi.admin.infrastracture.model.generated.UserUserExample;

@Service
public class AuthUserService implements UserDetailsService {
  @Autowired
  private UserUserMapper userUserMapper;

  @Override
  public AuthUser loadUserByUsername(String mail) throws UsernameNotFoundException {
    // 1. 認証されたユーザーをデータベースから検索する
    UserUserExample userUserExample = new UserUserExample();
    userUserExample.createCriteria().andMailEqualTo(mail).andDeleteFlagEqualTo(false);
    UserUser user = userUserMapper.selectByExample(userUserExample).get(0);

    // 2. 認証ユーザーとして返す情報をオブジェクトにつめ、返す
    AuthUser authUser = new AuthUser();
    authUser.setId(user.getId());
    authUser.setMail(user.getMail());
    authUser.setPassword(user.getPassword());

    return authUser;
  }
}

```

## WebSocket 通信実装方法

リアルタイムでのチャットでのやり取りを行うために、WebSocket 通信を行なっています。<br>
通信の実装方法は以下です。

1. `MessageMapping`アノテーションを付与したコントローラークラスを用意し、WebSocket 通信での受け口を確立する。<br>
   受け取ったリクエストパラメータに応じて、サービスクラスを紐づける。

```Java
package com.alichan.hostnavi.admin.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import com.alichan.hostnavi.admin.domain.service.ReservationMessageService;
import com.alichan.hostnavi.admin.dto.requestparam.ReservationMessageRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.WebSocketReservationMessageResponseData;

@Controller
public class ReservationMessageController {
  @Autowired
  private SimpMessagingTemplate template;
  @Autowired
  private ReservationMessageService reservationMessageService;

  // 1. MessageMappingアノテーションを付与し、WebSocket通信でのエンドポイントを指定する。
  @MessageMapping("/hostnavi-websocket")
  public void crudMessageWithWebSocket(
      @Validated ReservationMessageRequestParam reservationMessageRequestParam) {
    // 2. リクエストされたパラメータにイベントが格納されているため、そのイベントによって行う処理を変更する
    if (reservationMessageRequestParam.getEvent().equals("send")) {
      template.convertAndSend("/messages/" + reservationMessageRequestParam.getReservationId(),
          reservationMessageService.createMessage(reservationMessageRequestParam));
    } else if (reservationMessageRequestParam.getEvent().equals("input")) {
      template.convertAndSend("/messages/" + reservationMessageRequestParam.getReservationId(),
          reservationMessageService.inputMessage(reservationMessageRequestParam));
    }
  }
}

```

## 画像の保存処理実装方法

画像は MinIO で保存しています。<br>
MinIO 内でのディレクトリ構成、保存処理の実装方法は以下です。

- MinIO のディレクトリ構成

```lua
└── image -- 画像を格納するバケット
    ├── inn -- 宿泊施設に関する画像を格納するバケット
    │   ├── 1 -- 宿泊施設のIDごとのバケット
    │   │   ├── inn1.png -- 宿泊施設の画像
    │   │   └── inn2.png
    │   └── 2
    └── user -- ユーザーに関する画像を格納するバケット
        ├── 1 -- ユーザーのIDごとのバケット
        │   └── user1.png -- ユーザーの画像
        └── 2
```

- MinIO での画像保存処理を以下の様に実装

1. `application.yaml`で MinIO の接続に必要な情報を設定した環境変数を宣言。

```yaml
minio:
  endpoint: http://localhost:9000
  bucketName: image
  accessKey: alichan
  secretKey: alichan0609
```

2. `ImageLogic`クラスで、画像を保存する処理を実装

```Java
package com.alichan.hostnavi.admin.domain.logic;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.alichan.hostnavi.admin.dto.responsedata.ImageResponseData;
import com.alichan.hostnavi.admin.error.Assert;
import com.alichan.hostnavi.admin.util.JsonUtil;
import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import io.minio.SetBucketPolicyArgs;

@Component
public class ImageLogic {
  // 1. application.yaml設定した環境変数を変数に紐づける
  @Value("${minio.endpoint}")
  private String ENDPOINT;
  @Value("${minio.bucketName}")
  private String BUCKET_NAME;
  @Value("${minio.accessKey}")
  private String ACCESS_KEY;
  @Value("${minio.secretKey}")
  private String SECRET_KEY;

  // 2. 画像を保存する関数を作成する
  public Optional<ImageResponseData> createImage(MultipartFile image, String objectName) {
    try {
      // 3. MinIOへの処理を行うためにクライアントを宣言する。接続情報等を設定する。
      MinioClient minioClient =
          MinioClient.builder().endpoint(ENDPOINT).credentials(ACCESS_KEY, SECRET_KEY).build();
      // 4. 画像保存先のバケットが存在するか確認する
      boolean isExists =
          minioClient.bucketExists(BucketExistsArgs.builder().bucket(BUCKET_NAME).build());

      // 5. バケットが存在しなかったらバケットを作成
      if (!isExists) {
        String policy =
            JsonUtil.convertJsonToString("com/alichan/hostnavi/admin/minio/policy.json");

        minioClient.makeBucket(MakeBucketArgs.builder().bucket(BUCKET_NAME).build());
        minioClient.setBucketPolicy(
            SetBucketPolicyArgs.builder().bucket(BUCKET_NAME).config(policy).build());
      }

      // 6. 画像を保存先のバケットに保存
      minioClient.putObject(PutObjectArgs.builder().bucket(BUCKET_NAME).object(objectName)
          .stream(image.getInputStream(), image.getSize(), -1).contentType(image.getContentType())
          .build());

      // 7. 保存した画像名、画像を閲覧するためのURLを関数の返答として返す
      ImageResponseData imageResponseData = new ImageResponseData();
      imageResponseData.setName(image.getOriginalFilename());
      imageResponseData.setUrl(ENDPOINT + "/" + BUCKET_NAME + "/" + objectName);

      return Optional.of(imageResponseData);
    } catch (Exception error) {
      Assert.serverFail("画像のアップロードに失敗しました。");
      return null;
    }
  }

  // 8. 画像を削除する処理を作成する
  public void deleteObject(String objectName) {
    try {
      // 9. MinIOへの処理を行うためにクライアントを宣言する。接続情報等を設定する。
      MinioClient minioClient =
          MinioClient.builder().endpoint(ENDPOINT).credentials(ACCESS_KEY, SECRET_KEY).build();
      // 10. 画像をバケットから削除する
      minioClient
          .removeObject(RemoveObjectArgs.builder().bucket(BUCKET_NAME).object(objectName).build());
    } catch (Exception error) {
      Assert.serverFail("ファイルのアップロードに失敗しました。");
      return;
    }
  }

}
```

3. 上記クラスで作成した`createObject`メソッドや`deleteObject`メソッドを実際にサービスクラスで使用する。

```Java
package com.alichan.hostnavi.admin.domain.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alichan.hostnavi.admin.domain.logic.InnImageLogic;
import com.alichan.hostnavi.admin.dto.requestparam.ImagesRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.ImageResponseData;

@Service
public class InnInnService {
  @Autowired
  private InnInnLogic innInnLogic;

  /**
   * 宿泊施設の画像を複数作成する処理
   *
   * @param id 宿泊施設のid
   * @param images 宿泊施設の画像のリスト
   * @return APIのレスポンスデータとして整形された宿泊施設の画像のリスト
   */
  public List<ImageResponseData> createInnImage(long id, ImagesRequestParam images) {
    // 1. innImageLogic配下のcreateImageメソッドを使用して画像を保存する
    return innImageLogic.createImage(id, images);
  }
}

```

## API リクエスト時のパラメータのバリデーション実装方法

パラメータのバリデーションはリクエストパラメータを格納する dto 内のフィールド変数にアノテーションを付与することで実装しています。<br>
ライブラリが保持していないバリデーションについては、自作してアノテーションでバリデーションを付与できる様にしています。

- 自作バリデーションおよび対応するアノテーションの作成方法

1. バリデーションを実行するクラスを`ConstraintValidator`を拡張して作成する

```Java
package com.alichan.hostnavi.admin.application.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import com.alichan.hostnavi.admin.application.validation.annotation.LongType;

public class LongTypeValidator implements ConstraintValidator<LongType, Long> {
  // 1. isValid関数を上書きしてバリデーション実行時に有効であればtrue、無効であればfalseを返す様にする
  @Override
  public boolean isValid(Long value, ConstraintValidatorContext context) {
    return value instanceof Long || value == null;
  }
}
```

2. バリデーションに対応するアノテーションを作成する

```Java
package com.alichan.hostnavi.admin.application.validation.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
import com.alichan.hostnavi.admin.application.validation.validator.LongTypeValidator;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
// 1. 上記で作成したValidatorクラスを指定して、バリデーションを作成するアノテーションに対応させる
@Constraint(validatedBy = LongTypeValidator.class)
public @interface LongType {
  // 2. エラーメッセージとして返すメッセージを設定する
  String message() default "Long型の数値で入力してください";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
```

- リクエストパラメータへのバリデーション実装方法

1. リクエストパラメータを格納する dto 内の各フィールド変数にバリデーション用のアノテーションを付与する

```Java
package com.alichan.hostnavi.admin.dto.requestparam;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import com.alichan.hostnavi.admin.application.validation.annotation.IntegerType;
import com.alichan.hostnavi.admin.application.validation.annotation.LongType;
import com.alichan.hostnavi.admin.application.validation.annotation.StringType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InnInnRequestParam {
  // 1. 各フィールドに対応したバリデーションを実装するためのアノテーションをインポートして付与する
  @NotNull(message = "userIdを入力してください。")
  // 2. 自作したバリデーションをインポートして付与している
  @LongType(message = "userIdをLong型で入力してください。")
  @Min(value = 1, message = "userIdを1以上で入力してください。")
  private Long userId;
  @NotBlank(message = "nameを入力してください。")
  @StringType(message = "nameを文字列型で入力してください。")
  @Length(min = 1, max = 100, message = "nameを1文字以上100文字以下で入力してください。")
  private String name;
  @StringType(message = "descriptionを文字列型で入力してください。")
  @Length(min = 1, max = 500, message = "descriptionを1文字以上500文字以下で入力してください。")
  private String description;
  @NotNull(message = "feeを入力してください。")
  @IntegerType(message = "feeをInteger型で入力してください。")
  @Range(min = 0, max = 9999999, message = "feeを0以上9999999以下で入力してください。")
  private Integer fee;
}
```

2. コントローラークラスでリクエストパラメータを受け取る引数の前に、`@Validated`アノテーションを付与する

```Java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alichan.hostnavi.admin.application.response.Response;
import com.alichan.hostnavi.admin.domain.service.InnInnService;
import com.alichan.hostnavi.admin.dto.requestparam.InnInnRequestParam;
import com.alichan.hostnavi.admin.dto.responsedata.InnInnResponseData;

@Controller
@RequestMapping("/inns")
public class InnInnController {
  @Autowired
  private InnInnService innInnService;

  @PostMapping
  @ResponseBody
  public Response<InnInnResponseData> createInn(
    // 1. リクエストパラメータを受け取るinnInnの前に@Validatedアノテーションを付与することでリクエストパラメータにバリデーションをかける様にしている
    @Validated @RequestBody InnInnRequestParam innInn) {
    return Response.success(innInnService.createInn(innInn));
  }
}
```

## 環境変数の使用方法

環境変数は`application.yaml`で設定して使用します。<br>
宣言方法、使用方法は以下です。

1. `additional-spring-configuration-metadata.json`に追加で設定する環境変数を設定

```json
{
  "properties": [
    {
      "name": "minio.endpoint",
      "type": "java.lang.String",
      "description": "A description for 'minio.endpoint'"
    },
    {
      "name": "minio.bucketName",
      "type": "java.lang.String",
      "description": "A description for 'minio.bucketName'"
    },
    {
      "name": "minio.accessKey",
      "type": "java.lang.String",
      "description": "A description for 'minio.accessKey'"
    },
    {
      "name": "minio.secretKey",
      "type": "java.lang.String",
      "description": "A description for 'minio.secretKey'"
    }
  ]
}
```

1. `application.yaml`で環境変数を設定

```yaml
minio:
  endpoint: http://localhost:9000
  bucketName: image
  accessKey: alichan
  secretKey: alichan0609
```

2. 環境変数をフィールド変数に紐づけて使用する

```Java
package com.alichan.hostnavi.admin.domain.logic;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import com.alichan.hostnavi.admin.dto.responsedata.ImageResponseData;
import io.minio.MinioClient;

@Component
public class ImageLogic {
  // 1. application.yaml設定した環境変数をValueアノテーションを使用してフィールド変数に紐づける
  @Value("${minio.endpoint}")
  private String ENDPOINT;
  @Value("${minio.bucketName}")
  private String BUCKET_NAME;
  @Value("${minio.accessKey}")
  private String ACCESS_KEY;
  @Value("${minio.secretKey}")
  private String SECRET_KEY;

  public Optional<ImageResponseData> createImage(MultipartFile image, String objectName) {
      // 2. 実際に環境変数を使用する
      MinioClient minioClient =
          MinioClient.builder().endpoint(ENDPOINT).credentials(ACCESS_KEY, SECRET_KEY).build();
  }
}
```

## エラーハンドリングの実装方法

処理途中でエラーが発生した場合、API でエラーレスポンスを返す様にしています。<br>
実装方法は以下です。

1. 各 HTTP ステータスに対応するエラー時に発生するレスポンスを返すメソッドを定義する

```Java
package com.alichan.hostnavi.admin.application.response;

import lombok.Getter;

@Getter
public class Response<T> {
  private String message;
  private T data;

  private Response(String message, T data) {
    this.message = message;
    this.data = data;
  }

  // 1. デフォルトのエラーメッセージを設定したエラーレスポンスを返すメソッドを定義する
  public static <T> Response<T> failValidation() {
    return new Response<T>(ResponseMessageEnums.FAIL_VALIDATION.getMessage(), null);
  }

  // 2. カスタムのエラーメッセージを設定したエラーレスポンスを返すメソッドを定義する
  public static <T> Response<T> failValidation(String message) {
    return new Response<T>(message, null);
  }

  public static <T> Response<T> unauthorized() {
    return new Response<T>(ResponseMessageEnums.UNAUTHORIZED.getMessage(), null);
  }

  public static <T> Response<T> unauthorized(String message) {
    return new Response<T>(message, null);
  }

  public static <T> Response<T> forbbiden() {
    return new Response<T>(ResponseMessageEnums.FORBIDDEN.getMessage(), null);
  }

  public static <T> Response<T> forbbiden(String message) {
    return new Response<T>(message, null);
  }

  public static <T> Response<T> notFound() {
    return new Response<T>(ResponseMessageEnums.NOT_FOUND.getMessage(), null);
  }

  public static <T> Response<T> notFound(String message) {
    return new Response<T>(message, null);
  }

  public static <T> Response<T> serverFail() {
    return new Response<T>(ResponseMessageEnums.SERVER_FAIL.getMessage(), null);
  }

  public static <T> Response<T> serverFail(String message) {
    return new Response<T>(message, null);
  }
}

```

2. 各エラー時の HTTP ステータスに対応するエラーを作成する<br>

```Java
package com.alichan.hostnavi.admin.error.exception;

import com.alichan.hostnavi.admin.application.response.ResponseMessageEnums;
import lombok.Getter;

// 1. RuntimeExceptionクラスを拡張してエラー時のHTTPステータスに対応するエラークラスを作成する。このエラーはHTTPステータスが422のエラーに対応するクラス。
@Getter
public class FailValidationException extends RuntimeException {
  // 2. デフォルトで設定するエラーメッセージを設定する
  public FailValidationException() {
    super(ResponseMessageEnums.FAIL_VALIDATION.getMessage());
  }

  // 3. エラー発生時にエラーメッセージをカスタマイズできる様にする
  public FailValidationException(String message) {
    super(message);
  }
}
```

3. 各エラーを呼び出せる関数を作成する

```Java
package com.alichan.hostnavi.admin.error;

import com.alichan.hostnavi.admin.error.exception.FailValidationException;
import com.alichan.hostnavi.admin.error.exception.ForbbidenException;
import com.alichan.hostnavi.admin.error.exception.NotFoundException;
import com.alichan.hostnavi.admin.error.exception.ServerFailException;
import com.alichan.hostnavi.admin.error.exception.UnauthorizedException;

// 1. 各エラーを呼び出せる関数を作成する
public class Assert {
  // 2. エラーメッセージを設定しない場合に呼び出す関数を作成する
  public static void unAuthorized() {
    throw new UnauthorizedException();
  }

  // 3. エラーメッセージを設定する場合に呼び出す関数を作成する
  public static void unAuthorized(String message) {
    throw new UnauthorizedException(message);
  }

  public static void forbbiden() {
    throw new ForbbidenException();
  }

  public static void forbbiden(String message) {
    throw new ForbbidenException(message);
  }

  public static void notFound() {
    throw new NotFoundException();
  }

  public static void notFound(String message) {
    throw new NotFoundException(message);
  }

  public static void failedValidation() {
    throw new FailValidationException();
  }

  public static void failedValidation(String message) {
    throw new FailValidationException(message);
  }

  public static void serverFail() {
    throw new ServerFailException();
  }

  public static void serverFail(String message) {
    throw new ServerFailException(message);
  }

}
```

4. 各エラーをキャッチした際に API で対応するエラーレスポンスを返す様に設定する

```Java
package com.alichan.hostnavi.admin.error;

import org.apache.ibatis.jdbc.Null;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.alichan.hostnavi.admin.application.response.Response;
import com.alichan.hostnavi.admin.error.exception.FailValidationException;
import com.alichan.hostnavi.admin.error.exception.ForbbidenException;
import com.alichan.hostnavi.admin.error.exception.NotFoundException;
import com.alichan.hostnavi.admin.error.exception.ServerFailException;
import com.alichan.hostnavi.admin.error.exception.UnauthorizedException;

// 1. 各エラーをキャッチした際にAPIでエラーレスポンスを返す様に設定する
@ControllerAdvice
public class GlobalExceptionHandler {

  @ResponseBody
  // 2. エラー時に返すHTTPステータスを設定する
  @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
  // 3. どのエラーをキャッチした時に関数内の処理を実行してエラーレスポンスを返すか設定する。
  @ExceptionHandler(value = FailValidationException.class)
  public Response<Null> handleFailValidationException(FailValidationException error) {
    // 4. 対応するエラーレスポンスを返す様に設定する
    return Response.failValidation(error.getMessage());
  }

  @ResponseBody
  @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(value = UnauthorizedException.class)
  public Response<Null> handleUnauthorizedException(UnauthorizedException error) {
    return Response.unauthorized(error.getMessage());
  }

  @ResponseBody
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler(value = Exception.class)
  public Response<Null> handleException(Exception error) {
    return Response.serverFail();
  }

  @ResponseBody
  @ResponseStatus(code = HttpStatus.FORBIDDEN)
  @ExceptionHandler(value = ForbbidenException.class)
  public Response<Null> handleForbbidenException(ForbbidenException error) {
    return Response.forbbiden(error.getMessage());
  }

  @ResponseBody
  @ResponseStatus(code = HttpStatus.NOT_FOUND)
  @ExceptionHandler(value = NotFoundException.class)
  public Response<Null> handleNotFoundException(NotFoundException error) {
    return Response.notFound(error.getMessage());
  }

  @ResponseBody
  @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
  @ExceptionHandler(value = BindException.class)
  public Response<Null> handleBindException(BindException error) {
    return Response.failValidation(error.getBindingResult().getFieldError().getDefaultMessage());
  }

  @ResponseBody
  @ExceptionHandler(value = ServerFailException.class)
  @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
  public Response<Null> handleServerFailException(ServerFailException error) {
    return Response.serverFail(error.getMessage());
  }
}
```

## データベース処理の実装方法

データベース処理は OR マッパーである MyBatis を使用して実装しています。<br>
基本的な CRUD 処理を実行する SQL 文を設定した xml ファイル、紐づくメソッド、モデルは MyBatisGenerator で自動生成しています。<br>
それ以外にも自分でカスタマイズした SQL をメソッドで実行できる様、xml ファイルの作成、紐づくメソッド、モデルの実装もしています。<br>
それぞれの実装方法は以下です。

- MyBatisGenerator での CRUD メソッドの自動生成を行う方法

1. `generator.properties`ファイルで`generatorConfig.xml`に設定する MySQL への接続情報などを記載する

```proerties
jdbc.driverClass=com.mysql.cj.jdbc.Driver
jdbc.connectionURL=jdbc:mysql://localhost:3306/hostnavi?allowPublicKeyRetrieval=true&useSSL=false
jdbc.userId=root
jdbc.password=root
```

2. `generatorConfig.xml`で CRUD 処理を行うメソッドを生成する対象のデータベースへの接続情報、対象のテーブル情報等を設定する

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE generatorConfiguration
        PUBLIC "-//mybatis.org//DTD MyBatis Generator Configuration 1.0//EN"
        "http://mybatis.org/dtd/mybatis-generator-config_1_0.dtd">

<generatorConfiguration>
    <!-- 1. generator.propertiesを読み込む -->
    <properties resource="generator.properties"/>
    <context id="MySqlContext" targetRuntime="MyBatis3" defaultModelType="flat">
        <!-- 2. MySQLへの接続情報を設定する -->
        <jdbcConnection driverClass="${jdbc.driverClass}"
                        connectionURL="${jdbc.connectionURL}"
                        userId="${jdbc.userId}"
                        password="${jdbc.password}">
        </jdbcConnection>
        <!-- 3. 生成したモデルを格納する先のパッケージを指定する -->
        <javaModelGenerator targetPackage="com.alichan.hostnavi.admin.infrastracture.model.generated" targetProject="src/main/java"/>
        <!-- 4. 生成したマッパーインターフェースを格納する先のパッケージを指定する -->
        <sqlMapGenerator targetPackage="com.alichan.hostnavi.admin.infrastracture.mapper.generated" targetProject="src/main/resources"/>
        <!-- 5. 生成したマッパーxmlファイルを格納する先のパッケージを指定する -->
        <javaClientGenerator type="XMLMAPPER" targetPackage="com.alichan.hostnavi.admin.infrastracture.mapper.generated" targetProject="src/main/java"/>
        <!-- 6. CRUDメソッドを生成する対象のテーブルを指定する -->
        <table tableName="inn_inn">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
        </table>
        <table tableName="inn_amenity">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
        </table>
        <table tableName="inn_inn_amenity_relation">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
        </table>
        <table tableName="inn_facility">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
        </table>
        <table tableName="inn_inn_facility_relation">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
        </table>
        <table tableName="inn_image">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
        </table>
        <table tableName="inn_status">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
        </table>
        <table tableName="inn_type">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
        </table>
        <table tableName="inn_view">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
        </table>
        <table tableName="inn_page">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
        </table>
        <table tableName="reservation_reservation">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
        </table>
        <table tableName="reservation_message">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
        </table>
        <table tableName="reservation_review">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
        </table>
        <table tableName="reservation_status">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
        </table>
        <table tableName="user_user">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
        </table>
        <table tableName="user_credit_card">
            <generatedKey column="id" sqlStatement="MySql" identity="true"/>
        </table>
    </context>
</generatorConfiguration>
```

3. `Generator`クラスで CRUD メソッドを生成するための設定を行う

```Java
package com.alichan.hostnavi.admin.infrastracture;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

public class Generator {
  public static void main(String[] args) throws Exception {
    // 1. 警告を入れる配列を宣言する
    List<String> warnings = new ArrayList<String>();
    boolean overwrite = true;
    // 2. generatorConfig.xmlファイルを読み込む
    InputStream inputStream = Generator.class.getResourceAsStream("/generatorConfig.xml");
    ConfigurationParser configurationParser = new ConfigurationParser(warnings);
    Configuration configuration = configurationParser.parseConfiguration(inputStream);
    inputStream.close();

    // 3. 生成されたクラスやファイルが既に存在しても上書きされる様にする
    DefaultShellCallback defaultShellCallback = new DefaultShellCallback(overwrite);
    // 4. MyBatisGeneratorを作成する
    MyBatisGenerator myBatisGenerator =
        new MyBatisGenerator(configuration, defaultShellCallback, warnings);
    // 5. MyBatisGeneratorを実行する
    myBatisGenerator.generate(null);
    // 6. 実行時にエラーが起きた場合は出力する
    for (String warning : warnings) {
      System.out.println(warning);
    }
  }
}
```

4. `Generator`クラスを実行して、CRUD 処理を実行する SQL 文を設定した xml ファイル、紐づくメソッド、モデルを自動生成する

- カスタマイズした SQL をメソッドで実行できる様に設定する方法

1. SQL を xml ファイル内に書いて、紐づくメソッドと返すモデルを設定する

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- 1. 以下xmlを紐づけるマッパーインターフェース(SQLを実行するメソッドを格納するインターフェース)の場所を指定 -->
<mapper namespace="com.alichan.hostnavi.admin.infrastracture.mapper.custom.ReservationMessageCustomMapper">
    <!-- 2. メソッドから返すモデルの格納場所を指定 -->
	<resultMap id="ReservationMessageCustomResultMap" type="com.alichan.hostnavi.admin.infrastracture.model.custom.ReservationMessageCustom">
		<!-- 3. モデル内のフィールド変数と対応するカラム名の紐付けを行う -->
        <id column="id" jdbcType="BIGINT" property="id" />
		<result column="reservation_id" jdbcType="BIGINT" property="reservationId" />
		<result column="sender_id" jdbcType="BIGINT" property="senderId" />
		<result column="receiver_id" jdbcType="BIGINT" property="receiverId" />
		<result column="message" jdbcType="VARCHAR" property="message" />
		<result column="send_time" jdbcType="TIMESTAMP" property="sendTime" />
	</resultMap>
    <!-- 3. SQLとSQLを実行するメソッドの紐付けを行う -->
	<select id="selectSameReservationsLastMessages" resultMap="ReservationMessageCustomResultMap">
        SELECT
            message1.id,
            message1.reservation_id,
            message1.sender_id,
            message1.receiver_id,
            message1.message,
            message1.send_time
        FROM
            `reservation_message` message1
        WHERE
            send_time = (
			  SELECT MAX(send_time)
			  FROM `reservation_message` message2
			  WHERE message1.reservation_id = message2.reservation_id
			)
			AND message1.delete_flag = 0
    </select>
</mapper>
```

2. SQL を紐づけるメソッドを格納するマッパーインターフェースを作成する

```Java
package com.alichan.hostnavi.admin.infrastracture.mapper.custom;

import java.util.List;
import com.alichan.hostnavi.admin.infrastracture.model.custom.ReservationMessageCustom;

public interface ReservationMessageCustomMapper {
  List<ReservationMessageCustom> selectSameReservationsLastMessages();
}

```

3. SQL から返される結果を格納するモデルクラスを作成する

```Java
package com.alichan.hostnavi.admin.infrastracture.model.custom;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationMessageCustom {
  private Long id;
  private Long reservationId;
  private Long senderId;
  private Long receiverId;
  private String message;
  private Date sendTime;
}

```

4. 実際にサービスクラス内でカスタムメソッドを使用する

```Java
package com.alichan.hostnavi.admin.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alichan.hostnavi.admin.infrastracture.mapper.custom.ReservationMessageCustomMapper;
import com.alichan.hostnavi.admin.infrastracture.mapper.generated.ReservationMessageMapper;
import com.alichan.hostnavi.admin.infrastracture.model.custom.ReservationMessageCustom;
import com.alichan.hostnavi.admin.infrastracture.model.generated.ReservationMessage;
import com.alichan.hostnavi.admin.infrastracture.model.generated.ReservationMessageExample;
import com.alichan.hostnavi.admin.util.ModelMapperUtil;

@Service
public class ReservationMessageService {
  // 1. 使用するカスタムマッパーをフィールド変数に注入する
  @Autowired
  private ReservationMessageCustomMapper reservationMessageCustomMapper;

  public void getSameReservationsLastMessages(Long ownerId) {
    // 2. カスタムマッパー配下に格納されているカスタムメソッドを使用する
    List<ReservationMessageCustom> gottenMessages =
        reservationMessageCustomMapper.selectSameReservationsLastMessages();
  }
}

```

## テストの実装方法

現段階では API の入力・データベースのデータをテスト前に設定し、API の出力およびデータベースのデータをテスト後に確認する E2E テストを実装しています。<br>
今後認証が必要な API のテストや層ごとのユニットテストも実装予定です。<br>
実装方法およびテストの方法は以下です。

- テストの実装方法

1. テスト用 DB コンテナの作成

テスト用に実際にテストデータを何度も挿入しなすことを考慮し、テストに使用する DB コンテナを用意している。<br>
テスト用の DB コンテナの実装方法は今後追加予定のコンテナ環境構築方法の説明 README で説明するため、割愛。

2. csv ファイルでデータベースのデータのセットや確認が行える様に設定する`CsvDataSetLoader`クラスを作成する

```Java
package com.alichan.hostnavi.admin;

import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;
import org.springframework.core.io.Resource;
import com.github.springtestdbunit.dataset.AbstractDataSetLoader;

// 1. AbstractDataSetLoaderクラスを拡張してcsvをテスト前のデータベース内のデータとしてセットしたりテスト後のデータとして確認できる様にするCsvDataSetLoaderクラスを作成する
public class CsvDataSetLoader extends AbstractDataSetLoader {
  // 2. createDataSetを上書きして、DBUnitがcsvのパスからcsvを読み込める様にする
  @Override
  protected IDataSet createDataSet(Resource resource) throws Exception {
    return new CsvDataSet(resource.getFile());
  }
}
```

3. テスト時に必要なメソッドを格納した`Util`クラスを作成する

```Java
package com.alichan.hostnavi.admin;

import java.io.File;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.csv.CsvDataSet;
import org.dbunit.operation.DatabaseOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Util {
  @Value("${testdb.url}")
  private String DATABASE_URL;
  @Value("${testdb.user}")
  private String DATABASE_USER;
  @Value("${testdb.password}")
  private String DATABASE_PASSWORD;

  // 1. jsonファイルから内容を文字列として読み取って返すメソッドを作成する
  public String convertJsonFileToString(String path) {
    String jsonString = "";
    try (InputStream input =
        Thread.currentThread().getContextClassLoader().getResourceAsStream(path)) {
      ObjectMapper mapper = new ObjectMapper();
      JsonNode jsonNode = mapper.readValue(input, JsonNode.class);
      jsonString = mapper.writeValueAsString(jsonNode);

      return jsonString;
    } catch (Exception error) {
      return jsonString;
    }
  }

  // 2. テスト後にデータベース内のデータを全て消去・連番のIDを1番目に戻すという処理を行なった後、csvファイルを読み取ってデータをセットする処理を行うメソッドを作成する
  public void setUpDatabase(String path) {
    try (
        Connection connection =
            DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
        Statement statement = connection.createStatement();) {

      statement.execute("call truncateAllTable;");

      IDatabaseConnection databaseConnection = new DatabaseConnection(connection);

      IDataSet dataset = new CsvDataSet(new File(path));

      DatabaseOperation.INSERT.execute(databaseConnection, dataset);
    } catch (Exception error) {
      System.out.println(error.getMessage());
      throw new Error();
    }
  }

}
```

4. データベースに初期データをセットするための csv ファイルとテスト後のデータを確認するための csv ファイル、データベースにデータを設定する順番を記載した table-ordering.txt ファイルを作成する

- テスト前のデータベースの状態を設定する csv ファイルとデータベースにデータを設定する順番を記載した table-ordering.txt ファイル

```csv
"id","card_number","expiration_date","cvv","create_time","update_time","delete_flag"
1,"0000000000000000","2024-01-01 00:00:00","012","2023-01-01 00:00:00","2023-01-01 00:00:00",0

```

```csv
"id","image_url","name","description","address","occupation","phone_number","mail","password","credit_card_id","facebook_url","instagram_url","twitter_url","create_time","update_time","delete_flag"
1,"http://localhost:8080","山田太郎","素敵な人です。","神奈川県横浜市","エンジニア","01234567890","sample@sample.com","0123456789",1,"http://localhost:8080","http://localhost:8080","http://localhost:8080","2023-01-01 00:00:00","2023-01-01 00:00:00",0

```

```csv
"id","user_id","name","description","fee","status_id","type_id","address","guest_number","bedroom_number","bed_number","bathroom_number","create_time","update_time","delete_flag"

```

```txt
user_credit_card
user_user
inn_inn
```

- テスト後のデータベースの状態を確認する csv ファイルとデータベースにデータを設定する順番を記載した table-ordering.txt ファイル

```csv
"id","user_id","name","description","fee","status_id","type_id","address","guest_number","bedroom_number","bed_number","bathroom_number","delete_flag"
1,1,"石の家","素敵な宿です。",1000,1,1,"神奈川県横浜市",1,1,1,1,0
```

```csv
"id","inn_id","amenity_id","delete_flag"
1,1,1,0
2,1,2,0
```

```csv
"id","inn_id","facility_id","delete_flag"
1,1,1,0
2,1,2,0
```

```txt
inn_inn
inn_inn_amenity_relation
inn_inn_facility_relation
```

5. テスト時に実行する API の入力パラメータとして渡す json ファイル、出力として確認する json ファイルを作成する

- 入力パラメータとして私 json ファイル

```json
{
  "address": "神奈川県横浜市",
  "amenityIdList": [1, 2],
  "bathroomNumber": 1,
  "bedNumber": 1,
  "bedroomNumber": 1,
  "description": "素敵な宿です。",
  "facilityIdList": [1, 2],
  "fee": 1000,
  "guestNumber": 1,
  "name": "石の家",
  "statusId": 1,
  "typeId": 1,
  "userId": 1
}
```

- 出力として確認する json ファイル

```json
{
  "message": "成功",
  "data": {
    "id": 1,
    "user": {
      "id": 1,
      "imageUrl": "http://localhost:8080",
      "name": "山田太郎",
      "description": "素敵な人です。",
      "address": "神奈川県横浜市",
      "occupation": "エンジニア",
      "phoneNumber": "01234567890",
      "mail": "sample@sample.com",
      "facebookUrl": "http://localhost:8080",
      "instagramUrl": "http://localhost:8080",
      "twitterUrl": "http://localhost:8080",
      "creditCard": {
        "id": 1,
        "cardNumber": "0000000000000000",
        "expirationDate": "2024-01-01T00:00:00.000+09:00",
        "cvv": "012",
        "createTime": "2023-01-01T00:00:00.000+09:00",
        "updateTime": "2023-01-01T00:00:00.000+09:00"
      },
      "createTime": "2023-01-01T00:00:00.000+09:00",
      "updateTime": "2023-01-01T00:00:00.000+09:00"
    },
    "name": "石の家",
    "description": "素敵な宿です。",
    "fee": 1000,
    "status": {
      "id": 1,
      "name": "公開中"
    },
    "type": {
      "id": 1,
      "name": "一軒家"
    },
    "address": "神奈川県横浜市",
    "guestNumber": 1,
    "bedroomNumber": 1,
    "bedNumber": 1,
    "bathroomNumber": 1,
    "amenityList": [
      {
        "id": 1,
        "name": "歯ブラシ・歯磨き粉"
      },
      {
        "id": 2,
        "name": "シャンプー・コンディショナー"
      }
    ],
    "facilityList": [
      {
        "id": 1,
        "name": "Wi-Fi"
      },
      {
        "id": 2,
        "name": "テレビ"
      }
    ]
  }
}
```

6. 実際の各 API のテストを記載する`InnInnControllerTest`クラスを作成する

```Java
package com.alichan.hostnavi.admin;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.web.servlet.MockMvc;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

// 1. DBUnitでcsvを読み込むために、CsvDataSetLoaderクラスをdatasetLoaderに渡す
@DbUnitConfiguration(dataSetLoader = CsvDataSetLoader.class)
// 2. DIをテストコード内で使用できる様に指定するDependencyInjectionTestExecutionListenerクラスを渡す
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
// 3. MockMvcを利用するためにアノテーションを付与する
@AutoConfigureMockMvc
// 4. テスト対象となるクラスをclassesに渡す
@SpringBootTest(classes = {HostNaviAdminApplication.class})
public class InnInnControllerTest {
  // 5. mockMvcおよびutilクラスをDIする
  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private Util util;

  // 6. 各テストケースの関連ファイルが格納されているルートパスを定数として定義する
  private final String rootPath1 = "src/test/resources/";
  private final String rootPath2 = "com/alichan/hostnavi/admin/";
  // 7. 各テストケースの関連ファイルが格納されているパスを定数として定義する
  private final String createTest1FilePath = "innInn/create/test1/";
  private final String deleteTest1FilePath = "innInn/delete/test1/";
  private final String getTest1FilePath = "innInn/get/test1/";
  private final String getTest2FilePath = "innInn/get/test2/";
  private final String getTest3FilePath = "innInn/get/test3/";
  private final String updateTest1FilePath = "innInn/update/test1/";

  // 8. テストケースとして認識される様、Testアノテーションを付与する
  @Test
  // 9. テスト後のデータベース内のデータを確認するために、予想されているデータベースのデータを格納したcsvファイルのパスをvalueに渡す
  @ExpectedDatabase(value = createTest1FilePath + "database/expected",
      assertionMode = DatabaseAssertionMode.NON_STRICT)
  public void createTest1() throws Exception {
    // 10. データベースの初期化処理と、初期データのセットを行う
    util.setUpDatabase(rootPath1 + rootPath2 + createTest1FilePath + "database/setUp");

    // 11. テストの際に渡すリクエストパラメータとして用意されたjsonファイルを読み込み、文字列としてrequestParamに渡す
    String requestParam =
        util.convertJsonFileToString(rootPath2 + createTest1FilePath + "requestParam.json");
    // 12. 予想レスポンスとして用意されたjsonファイルを読み込み、文字列としてresponseDataに渡す
    String responseData =
        util.convertJsonFileToString(rootPath2 + createTest1FilePath + "responseData.json");

    // 13. ヘッダー、リクエストパラメータを設定、ポストリクエストを行う
    mockMvc.perform(post("/inns").contentType(MediaType.APPLICATION_JSON).content(requestParam))
        // 14. テスト結果の出力を行う
        .andDo(print())
        // 15. ステータスの出力が予想と異なるか確認する
        .andExpect(status().isOk())
        // 16. コンテンツタイプが予想と異なるか確認する
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        // 17. レスポンスが予想と異なるか確認する
        .andExpect(content().json(responseData, false));
  }

  ...

```

- テストの実施方法

1. 以下コマンドで本番用のデータベースコンテナを停止する

```bash
$ docker stop mysql8.0
```

2. 以下コマンドでテスト用のデータベースコンテナを起動する

```bash
$ docker start testmysql8.0
```

3. `hostnavi-docker`配下で、以下コマンドを実行して、MySQL のデータを初期化する。

```bash
$ ./test-mysql.sh
```

4. テストをするために`WebSecurityConfig`クラスで設定している`inns`配下のパスにかかっている認証を設定している箇所をコメントアウトすることで外し、認証をせずにリクエストを全て許可する用設定する

```Java
package com.alichan.hostnavi.admin.application.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    ...
    http.authorizeHttpRequests(auth -> {
      // 1. inns配下のパスにかかっている認証をコメントアウトすることで外す
      // auth.antMatchers("/inns/**").authenticated();
      // 2. inns配下のパスに対するリクエストを全て許可する用設定
      auth.antMatchers("/inns/**").permitAll();
    });
    ...
  }
}
```

5. エクリプスでパッケージ・エクスプローラー配下の`src/test/java`を右クリックし、実行>JUnit テストを押下、テストケースが全て通ればテスト成功
