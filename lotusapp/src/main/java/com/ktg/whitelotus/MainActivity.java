package com.ktg.whitelotus;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends Activity {

    private static final int NAVY = Color.rgb(2, 11, 29);
    private static final int NAVY_2 = Color.rgb(5, 24, 55);
    private static final int BLUE = Color.rgb(8, 45, 92);
    private static final int GOLD = Color.rgb(216, 182, 108);
    private static final int GOLD_DARK = Color.rgb(117, 76, 24);
    private static final int CREAM = Color.rgb(248, 238, 210);
    private static final int PALE = Color.rgb(219, 229, 244);
    private static final int MUTED = Color.rgb(155, 179, 211);
    private static final int CARD = Color.argb(215, 4, 19, 45);

    private FrameLayout root;
    private SharedPreferences prefs;
    private String currentScreen = "intro";

    private final List<BookItem> books = new ArrayList<BookItem>();
    private final List<PersonItem> people = new ArrayList<PersonItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window w = getWindow();
        w.setStatusBarColor(NAVY);
        w.setNavigationBarColor(NAVY);
        prefs = getSharedPreferences("white_lotus_reader", MODE_PRIVATE);
        seedData();
        showIntro();
    }

    private void seedData() {
        books.add(new BookItem("Книга А", "Восхождение Белоснежного Звёздного Лотоса Всего и Всея", "В содержании Тома указано начало на странице 11. Книга связана с собственным Лотосовым Кремлём и первой девочкой-писцом — Главой Дворца Книги А."));
        books.add(new BookItem("Книга Б", "Восхождение Пути Белоснежного Звёздного Лотоса", "В содержании Тома указано начало на странице 14. Для Книги Б показаны отдельный Лотосовый Кремль и своя девочка-писец."));
        books.add(new BookItem("Книга В", "Воздвижение Восхождения Восьми Великих Восхождений", "В содержании Тома указано начало на странице 39. В визуальной системе Тома книга соединяет огненные и бело-звёздные мотивы."));
        books.add(new BookItem("Книга Г", "Восхождение и Преобразование Великого Белоснежного Звёздного Лотоса", "В содержании Тома указано начало на странице 92. Четвёртая девочка-писец записывала Книгу Г и является Главой её Дворца."));
        books.add(new BookItem("Книга Д", "Входящая Храмовая Ступень Возрождающегося Лотоса", "В содержании Тома указано начало на странице 134. Пятая девочка-писец связана с Книгой Д и её Дворцом."));
        books.add(new BookItem("Раздел Тома", "Знакомство Белоснежного Звёздного Лотоса с Ожидающим Её Семьёй Звёздного Лотоса", "В содержании этот раздел указан с началом на странице 329. В первой версии приложения мы сохраняем формулировку источника без переименования."));
        books.add(new BookItem("Книга Е", "Поведывания Белоснежному Золотому Лотосу Всего от Малых Белоснежных Лотосов", "В содержании Тома указано начало на странице 549. Шестая девочка-писец является Главой Дворца Книги Е."));
        books.add(new BookItem("Книга Ё", "Приход Гостьи и ожидания Гостей, которые преобразовали Малых Белоснежных Лотосов", "В содержании Тома Книга Ё следует после Книги Е. Седьмая девочка-писец является Главой Дворца Книги Ё."));
        books.add(new BookItem("Книга Ж", "Приход Старшей Дочери Белоснежного Звёздного Лотоса Всего и Всея", "В содержании Тома указано начало на странице 837. Восьмая девочка-писец является Главой Дворца Книги Ж."));
        books.add(new BookItem("Книга З", "Слушатель и Писец настоящего Тома и как этот Том явился на этой Изюминке Вселенной", "В содержании Тома указано начало на странице 861. Раздел посвящён слушателю, писцу и появлению настоящего Тома."));

        people.add(new PersonItem("Первая девочка-писец", "Записывала Книгу А; Глава Дворца Книги А."));
        people.add(new PersonItem("Вторая девочка-писец", "Записывала Книгу Б; Глава Дворца Книги Б."));
        people.add(new PersonItem("Третья девочка-писец", "Записывала Книгу В; Глава Дворца Книги В."));
        people.add(new PersonItem("Четвёртая девочка-писец", "Записывала Книгу Г; Глава Дворца Книги Г."));
        people.add(new PersonItem("Пятая девочка-писец", "Записывала Книгу Д; Глава Дворца Книги Д."));
        people.add(new PersonItem("Шестая девочка-писец", "Записывала Книгу Е; Глава Дворца Книги Е."));
        people.add(new PersonItem("Седьмая девочка-писец", "Записывала Книгу Ё; Глава Дворца Книги Ё."));
        people.add(new PersonItem("Восьмая девочка-писец", "Записывала Книгу Ж; Глава Дворца Книги Ж."));
        people.add(new PersonItem("Девятая девочка", "Осуществляет контроль за всеми, кто писал Книги; Глава Кремля контроля."));
        people.add(new PersonItem("Десятая девочка-писец", "Осуществляет надзор за всеми, кто писал; Глава Кремля надзора."));
        people.add(new PersonItem("Одиннадцатая девочка-писец", "Осуществляет проверки всех, кто писал; Глава Кремля проверки."));
        people.add(new PersonItem("Двенадцатая девочка-писец", "Глашатай Тома, Советник дальнейшего оглашения Тома А; Глава Хранитель и Глава Кремля Тома А."));
        people.add(new PersonItem("Хранительницы и Распределительницы", "Связаны с Лотосовыми Кремлями отдельных Книг и хранением книжного комплекса."));
        people.add(new PersonItem("Хранительница Архива", "Девочка, которая сохраняет и держит управление Архива Всех Книг."));
    }

    private void showIntro() {
        currentScreen = "intro";
        LinearLayout page = page();
        page.setGravity(Gravity.CENTER_HORIZONTAL);
        page.addView(space(72));
        page.addView(lotusMark(112));
        page.addView(title("БЕЛОСНЕЖНАЯ\nИЗБУШКА", 35));
        page.addView(subtitle("Том А · Бутон Белоснежного Звёздного Золотого Лотоса", 18));
        page.addView(space(22));
        page.addView(bodyText("Интерактивное путешествие по Книгам, Дворцам, Лотосовым Кремлям, девочкам-писцам и дороге изложения.", 17));
        page.addView(space(30));
        Button start = goldButton(prefs.getBoolean("registered", false) ? "Войти во Дворец" : "Начать путешествие");
        start.setOnClickListener(v -> {
            if (prefs.getBoolean("registered", false)) showPalace();
            else showRegistration();
        });
        page.addView(start);
        page.addView(space(16));
        TextView note = bodyText("Версия 0.1 · Android 7.0+ · первая конструкция приложения по архитектуре Тома", 13);
        note.setTextColor(MUTED);
        page.addView(note);
        render(page);
    }

    private void showRegistration() {
        currentScreen = "registration";
        LinearLayout page = page();
        page.addView(space(24));
        page.addView(lotusMark(76));
        page.addView(title("ПРЕДСТАВЬТЕСЬ", 28));
        page.addView(subtitle("Регистрация перед входом во Дворец", 16));
        page.addView(space(18));

        LinearLayout cloud = card();
        TextView cloudTitle = heading("Регистрация", 24);
        cloud.addView(cloudTitle);
        cloud.addView(bodyText("Поля сохраняются только на этом устройстве. Оплата в этой демонстрационной версии не проводится.", 14));
        cloud.addView(space(14));

        EditText name = input("Имя", InputType.TYPE_CLASS_TEXT);
        name.setText(prefs.getString("name", ""));
        cloud.addView(name);
        EditText email = input("Почта", InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        email.setText(prefs.getString("email", ""));
        cloud.addView(email);
        EditText country = input("Страна", InputType.TYPE_CLASS_TEXT);
        country.setText(prefs.getString("country", "Узбекистан"));
        cloud.addView(country);

        TextView payLabel = smallLabel("Оплата");
        cloud.addView(payLabel);
        Spinner payment = new Spinner(this);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,
                new String[]{"Демо-доступ — без оплаты", "Подписка — пока не подключена"});
        payment.setAdapter(adapter);
        payment.setBackground(inputBackground());
        LinearLayout.LayoutParams sp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(54));
        sp.setMargins(0, dp(6), 0, dp(16));
        payment.setLayoutParams(sp);
        cloud.addView(payment);

        Button register = goldButton("Зарегистрироваться");
        register.setOnClickListener(v -> {
            String n = name.getText().toString().trim();
            if (n.length() == 0) {
                name.setError("Введите имя");
                return;
            }
            prefs.edit()
                    .putBoolean("registered", true)
                    .putString("name", n)
                    .putString("email", email.getText().toString().trim())
                    .putString("country", country.getText().toString().trim())
                    .apply();
            showRegistrationSuccess();
        });
        cloud.addView(register);
        page.addView(cloud);
        page.addView(space(12));
        Button back = ghostButton("Назад");
        back.setOnClickListener(v -> showIntro());
        page.addView(back);
        render(page);
    }

    private void showRegistrationSuccess() {
        currentScreen = "registered";
        LinearLayout page = page();
        page.addView(space(45));
        page.addView(lotusMark(86));
        page.addView(title("РЕГИСТРАЦИЯ\nЗАВЕРШЕНА", 27));
        page.addView(space(18));

        LinearLayout parchment = new LinearLayout(this);
        parchment.setOrientation(LinearLayout.VERTICAL);
        parchment.setPadding(dp(24), dp(26), dp(24), dp(26));
        parchment.setGravity(Gravity.CENTER_HORIZONTAL);
        GradientDrawable parchmentBg = new GradientDrawable();
        parchmentBg.setColor(Color.rgb(241, 222, 178));
        parchmentBg.setCornerRadius(dp(24));
        parchmentBg.setStroke(dp(2), GOLD_DARK);
        parchment.setBackground(parchmentBg);
        LinearLayout.LayoutParams pp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        pp.setMargins(dp(12), dp(8), dp(12), dp(14));
        parchment.setLayoutParams(pp);

        TextView pt = new TextView(this);
        pt.setText("Регистрация");
        pt.setTextSize(28);
        pt.setTextColor(Color.rgb(72, 45, 14));
        pt.setTypeface(Typeface.create("serif", Typeface.BOLD));
        parchment.addView(pt);
        parchment.addView(parchmentLine("Имя", prefs.getString("name", "—")));
        parchment.addView(parchmentLine("Почта", emptyDash(prefs.getString("email", ""))));
        parchment.addView(parchmentLine("Страна", emptyDash(prefs.getString("country", ""))));
        parchment.addView(parchmentLine("Оплата", "Демо-доступ"));
        page.addView(parchment);

        TextView invite = heading("Пройдёмте во Дворец", 22);
        page.addView(invite);
        page.addView(bodyText("Внутри вас ждут Главная палата, Книги Тома А, Архив Всех Книг, персонажи, Дворцы, Путь Лотоса и Читальный зал.", 16));
        page.addView(space(20));
        Button enter = goldButton("Войти во Дворец");
        enter.setOnClickListener(v -> showPalace());
        page.addView(enter);
        render(page);
    }

    private void showPalace() {
        currentScreen = "palace";
        LinearLayout page = page();
        page.addView(space(18));
        page.addView(lotusMark(72));
        String name = prefs.getString("name", "Гость");
        page.addView(title("ГЛАВНАЯ ПАЛАТА", 28));
        page.addView(subtitle("Добро пожаловать, " + name + ", внутрь Дворца Тома А", 16));
        page.addView(space(18));

        page.addView(menuCard("Книги Тома А", "Войти в каталог Книг и связанных с ними Дворцов.", "Открыть книги", v -> showBooks()));
        page.addView(menuCard("Архив Всех Книг", "Единое место для поиска по Книгам, хранителям и ключевым понятиям.", "Перейти в архив", v -> showArchive()));
        page.addView(menuCard("Девочки-писцы и Хранительницы", "Кто записывал Книги, кто управляет Дворцами, Кремлями, контролем, надзором и Архивом.", "Открыть персонажей", v -> showPeople()));
        page.addView(menuCard("Дворцы и Лотосовые Кремли", "Навигационная карта книжного комплекса: Книга → Дворец → Кремль → Хранительница.", "Открыть Дворцы", v -> showPalaces()));
        page.addView(menuCard("Путь Лотоса", "Путеводитель по омовениям, преобразованиям, храмам и ключевым этапам дороги изложения.", "Начать путь", v -> showPath()));
        page.addView(menuCard("Читальный зал", "Спокойный режим чтения с выдержками, закладками и регулировкой размера текста.", "Войти в зал", v -> showReadingHall()));
        page.addView(space(6));
        Button reset = ghostButton("Сменить регистрацию");
        reset.setOnClickListener(v -> showRegistration());
        page.addView(reset);
        render(page);
    }

    private void showBooks() {
        currentScreen = "books";
        LinearLayout page = sectionPage("КНИГИ ТОМА А", "Каталог построен по содержанию загруженного Тома. Формулировки сохранены максимально близко к источнику.");
        for (BookItem b : books) {
            LinearLayout c = card();
            TextView tag = smallLabel(b.label);
            c.addView(tag);
            c.addView(heading(b.title, 20));
            TextView desc = bodyText(b.note, 14);
            desc.setTextColor(PALE);
            c.addView(desc);
            Button open = goldButton("Открыть в читальном зале");
            open.setOnClickListener(v -> showReaderForBook(b));
            c.addView(open);
            page.addView(c);
        }
        page.addView(backHomeButton());
        render(page);
    }

    private void showPeople() {
        currentScreen = "people";
        LinearLayout page = sectionPage("ПЕРСОНАЖИ И ХРАНИТЕЛЬНИЦЫ", "В Томе книжная структура персонализирована: отдельные девочки-писцы связаны с отдельными Книгами и Дворцами, а также с функциями контроля, надзора, проверки и хранения.");
        for (PersonItem p : people) {
            LinearLayout c = card();
            c.addView(heading(p.name, 19));
            c.addView(bodyText(p.role, 15));
            page.addView(c);
        }
        page.addView(backHomeButton());
        render(page);
    }

    private void showArchive() {
        currentScreen = "archive";
        LinearLayout page = sectionPage("АРХИВ ВСЕХ КНИГ", "По книге Архив имеет собственное управление. В приложении Архив становится главным поисковым узлом по Книгам, девочкам-писцам, Хранительницам и понятиям.");

        EditText query = input("Поиск по Архиву", InputType.TYPE_CLASS_TEXT);
        page.addView(query);
        LinearLayout results = new LinearLayout(this);
        results.setOrientation(LinearLayout.VERTICAL);
        page.addView(results);
        Runnable refresh = () -> fillArchiveResults(results, query.getText().toString());
        query.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) { refresh.run(); }
            @Override public void afterTextChanged(Editable s) {}
        });
        refresh.run();
        page.addView(backHomeButton());
        render(page);
    }

    private void fillArchiveResults(LinearLayout results, String q) {
        results.removeAllViews();
        String needle = q == null ? "" : q.trim().toLowerCase(Locale.ROOT);
        int count = 0;
        for (BookItem b : books) {
            String hay = (b.label + " " + b.title + " " + b.note).toLowerCase(Locale.ROOT);
            if (needle.length() == 0 || hay.contains(needle)) {
                results.addView(archiveRow("КНИГА", b.label + " · " + b.title, v -> showReaderForBook(b)));
                count++;
            }
        }
        for (PersonItem p : people) {
            String hay = (p.name + " " + p.role).toLowerCase(Locale.ROOT);
            if (needle.length() > 0 && hay.contains(needle)) {
                results.addView(archiveRow("ПЕРСОНАЖ", p.name + " — " + p.role, v -> showPeople()));
                count++;
            }
        }
        if (count == 0) {
            TextView empty = bodyText("В Архиве пока нет совпадений. Попробуйте слова: Лотос, Книга А, писец, Кремль, Хранительница.", 15);
            results.addView(empty);
        }
    }

    private void showPalaces() {
        currentScreen = "palaces";
        LinearLayout page = sectionPage("ДВОРЦЫ И ЛОТОСОВЫЕ КРЕМЛИ", "Вместо обычной папочной навигации приложение использует пространственную логику самой книги: у Книг есть собственные Дворцы, а хранение связано с Лотосовыми Кремлями.");
        String[] rows = {
                "Книга А → Дворец Книги А → Лотосовый Кремль Книги А → Хранительница и Распределительница",
                "Книга Б → Дворец Книги Б → Лотосовый Кремль Книги Б → Хранительница и Распределительница",
                "Книга В → Дворец Книги В → Лотосовый Кремль Книги В → Хранительница и Распределительница",
                "Книга Г → Дворец Книги Г → Лотосовый Кремль Книги Г → Хранительница и Распределительница",
                "Книга Д → Дворец Книги Д → Лотосовый Кремль Книги Д → Хранительница и Распределительница",
                "Книга Е → Дворец Книги Е → Лотосовый Кремль Книги Е → Хранительница и Распределительница",
                "Книга Ё → Дворец Книги Ё → Лотосовый Кремль Книги Ё → Хранительница и Распределительница",
                "Книга Ж → Дворец Книги Ж → Лотосовый Кремль Книги Ж → Хранительница и Распределительница",
                "Кремль контроля → Девятая девочка",
                "Кремль надзора → Десятая девочка-писец",
                "Кремль проверки → Одиннадцатая девочка-писец",
                "Кремль Тома А → Двенадцатая девочка-писец, Глашатай и Глава Хранитель",
                "Архив Всех Книг → Девочка, сохраняющая и управляющая Архивом"
        };
        for (String r : rows) {
            LinearLayout c = card();
            c.addView(bodyText(r, 16));
            page.addView(c);
        }
        page.addView(backHomeButton());
        render(page);
    }

    private void showPath() {
        currentScreen = "path";
        LinearLayout page = sectionPage("ПУТЬ ЛОТОСА", "Путеводитель составлен по отдельным аспектам и моментам, перечисленным в содержании Тома. Это не пересказ, а навигационный слой для дальнейшего чтения.");
        String[] steps = {
                "Омовение Великого Небесного Лотоса в Древнем Храме",
                "Первое Преобразование Великого Небесного Лотоса",
                "Второе омовение и Ветерковые, Ураганчиковые, Водные, Огненные, Пыльцовые, Цветочные, Кувшинковые, Лотосные, Песочные, Лепестковые и другие книги",
                "Третье Омовение и Преобразование Верховного Небесного Лотоса",
                "Последующие омовения и преобразования",
                "Пятое Омовение и Преобразование",
                "Седьмое Омовение и Преобразование",
                "Следование в Дальний Горный Храм Детства Верховного Небесного Лотоса",
                "Гости Верховного Небесного Лотоса после омовения и Преобразования",
                "Большая Родина Бабушки Верховного Небесного Лотоса"
        };
        for (int i = 0; i < steps.length; i++) {
            LinearLayout row = new LinearLayout(this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setGravity(Gravity.TOP);
            row.setPadding(dp(4), dp(8), dp(4), dp(8));
            TextView n = new TextView(this);
            n.setText(String.valueOf(i + 1));
            n.setTextColor(NAVY);
            n.setTextSize(16);
            n.setGravity(Gravity.CENTER);
            n.setTypeface(Typeface.DEFAULT_BOLD);
            GradientDrawable circle = new GradientDrawable();
            circle.setShape(GradientDrawable.OVAL);
            circle.setColor(GOLD);
            n.setBackground(circle);
            row.addView(n, new LinearLayout.LayoutParams(dp(36), dp(36)));
            TextView text = bodyText(steps[i], 16);
            LinearLayout.LayoutParams tp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
            tp.setMargins(dp(12), 0, 0, 0);
            text.setLayoutParams(tp);
            row.addView(text);
            page.addView(row);
        }
        page.addView(backHomeButton());
        render(page);
    }

    private void showReadingHall() {
        currentScreen = "reading";
        LinearLayout page = sectionPage("ЧИТАЛЬНЫЙ ЗАЛ", "Первая сборка показывает, как читать этот большой Том внутри приложения: смысловыми дорожками, не теряя Книги, Дворцы и вопросы из поля зрения.");

        String[][] chapters = {
                {"Предисловие", "Предисловие объясняет принцип передачи текста: составители подчёркивают, что стремились сохранить изложение в полученном порядке, без дополнительных трактовок. Лотосовые перья, пергаменты и хранение текста внутри самой книги выступают частью её собственной картины мира."},
                {"Дорога изложения", "В Томе способ повествования описывается как шахматно-датированный, шахматно-ярусный и причинно-ситуационный: ситуация приводит к вопросу, вопрос — к ответу, а затем раскрываются связанные причины, следствия и другие позиции."},
                {"Девочка-писец", "Изложение представлено как дошедшее до читателя через девочку-писца. Позже содержание отдельно показывает девочек-писцов, связанных с Книгами и Дворцами, а также фигуры контроля, надзора, проверки и хранения."},
                {"Лотосовые перья и пергаменты", "В предисловии говорится о лотосовых перьях и лотосовых пергаментах как о способе записи внутри повествовательного мира Тома. Поэтому в интерфейсе заметки, регистрация и закладки оформляются как пергаменты и лепестки, а не как обычные системные карточки."},
                {"Вопросы и ответы", "В ряде сцен подчёркивается необходимость внимательно слушать, задавать вопросы, уточнять их и искать ответы. Поэтому приложение предусматривает отдельный будущий слой «Вопросы к прочитанному», связанный с конкретным местом дороги изложения."}
        };

        for (String[] ch : chapters) {
            LinearLayout c = card();
            c.addView(heading(ch[0], 20));
            c.addView(bodyText(ch[1], 16));
            Button open = goldButton("Читать");
            open.setOnClickListener(v -> showTextReader(ch[0], ch[1]));
            c.addView(open);
            page.addView(c);
        }
        TextView scope = bodyText("Важно: APK 0.1 содержит архитектуру приложения, каталог и подготовленные читальные выдержки. Полный текст 1133-страничного PDF пока не встроен внутрь APK — это следующий этап после проверки конструкции на устройстве.", 14);
        scope.setTextColor(MUTED);
        page.addView(scope);
        page.addView(backHomeButton());
        render(page);
    }

    private void showReaderForBook(BookItem b) {
        String text = b.note + "\n\nВ приложении эта Книга рассматривается не как отдельный файл, а как часть Дворца Тома. Для полной версии здесь будет отображаться оригинальный текст соответствующей Книги, сохранение последнего места чтения, закладки, связанные персонажи, Дворец и Лотосовый Кремль.\n\nСейчас вы видите первый рабочий читальный режим, предназначенный для проверки навигации и визуального языка на Android 7+.";
        showTextReader(b.label + " · " + b.title, text);
    }

    private void showTextReader(String headingText, String text) {
        currentScreen = "reader";
        LinearLayout page = sectionPage("ЧИТАЛЬНЫЙ ЗАЛ", headingText);
        LinearLayout parchment = new LinearLayout(this);
        parchment.setOrientation(LinearLayout.VERTICAL);
        parchment.setPadding(dp(22), dp(24), dp(22), dp(24));
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.rgb(238, 226, 199));
        bg.setStroke(dp(1), Color.rgb(151, 112, 52));
        bg.setCornerRadius(dp(18));
        parchment.setBackground(bg);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        p.setMargins(0, dp(8), 0, dp(16));
        parchment.setLayoutParams(p);

        TextView read = new TextView(this);
        read.setText(text);
        read.setTextColor(Color.rgb(52, 38, 23));
        read.setTextSize(prefs.getFloat("reader_size", 19f));
        read.setLineSpacing(0, 1.35f);
        read.setTypeface(Typeface.create("serif", Typeface.NORMAL));
        read.setTextIsSelectable(true);
        parchment.addView(read);
        page.addView(parchment);

        LinearLayout controls = new LinearLayout(this);
        controls.setOrientation(LinearLayout.HORIZONTAL);
        controls.setGravity(Gravity.CENTER);
        Button minus = ghostButton("A−");
        Button plus = ghostButton("A+");
        Button bookmark = goldButton(prefs.getBoolean("bookmark_" + headingText.hashCode(), false) ? "Закладка сохранена" : "Сохранить закладку");
        LinearLayout.LayoutParams cp = new LinearLayout.LayoutParams(0, dp(48), 1f);
        cp.setMargins(dp(3), dp(3), dp(3), dp(3));
        minus.setLayoutParams(cp);
        plus.setLayoutParams(cp);
        controls.addView(minus);
        controls.addView(plus);
        page.addView(controls);
        page.addView(bookmark);

        minus.setOnClickListener(v -> {
            float sz = Math.max(14f, read.getTextSize() / getResources().getDisplayMetrics().scaledDensity - 1f);
            read.setTextSize(sz);
            prefs.edit().putFloat("reader_size", sz).apply();
        });
        plus.setOnClickListener(v -> {
            float sz = Math.min(30f, read.getTextSize() / getResources().getDisplayMetrics().scaledDensity + 1f);
            read.setTextSize(sz);
            prefs.edit().putFloat("reader_size", sz).apply();
        });
        bookmark.setOnClickListener(v -> {
            String key = "bookmark_" + headingText.hashCode();
            boolean now = !prefs.getBoolean(key, false);
            prefs.edit().putBoolean(key, now).apply();
            bookmark.setText(now ? "Закладка сохранена" : "Сохранить закладку");
            Toast.makeText(this, now ? "Лотосовая закладка сохранена" : "Закладка удалена", Toast.LENGTH_SHORT).show();
        });

        Button hall = ghostButton("Вернуться в Читальный зал");
        hall.setOnClickListener(v -> showReadingHall());
        page.addView(hall);
        Button home = ghostButton("Главная палата");
        home.setOnClickListener(v -> showPalace());
        page.addView(home);
        render(page);
    }

    private LinearLayout sectionPage(String title, String intro) {
        LinearLayout page = page();
        page.addView(space(16));
        page.addView(lotusMark(64));
        page.addView(title(title, 26));
        TextView t = bodyText(intro, 15);
        t.setTextColor(PALE);
        page.addView(t);
        page.addView(space(8));
        return page;
    }

    private LinearLayout page() {
        LinearLayout page = new LinearLayout(this);
        page.setOrientation(LinearLayout.VERTICAL);
        page.setPadding(dp(18), dp(18), dp(18), dp(38));
        page.setGravity(Gravity.CENTER_HORIZONTAL);
        return page;
    }

    private void render(View content) {
        root = new FrameLayout(this);
        StarFieldView stars = new StarFieldView(this);
        root.addView(stars, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ScrollView scroll = new ScrollView(this);
        scroll.setFillViewport(true);
        scroll.setOverScrollMode(View.OVER_SCROLL_NEVER);
        scroll.addView(content, new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        root.addView(scroll, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(root);
    }

    private TextView title(String s, int size) {
        TextView t = new TextView(this);
        t.setText(s);
        t.setGravity(Gravity.CENTER);
        t.setTextColor(CREAM);
        t.setTextSize(size);
        t.setTypeface(Typeface.create("serif", Typeface.BOLD));
        t.setLetterSpacing(0.08f);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        p.setMargins(0, dp(8), 0, dp(6));
        t.setLayoutParams(p);
        return t;
    }

    private TextView heading(String s, int size) {
        TextView t = new TextView(this);
        t.setText(s);
        t.setTextColor(CREAM);
        t.setTextSize(size);
        t.setTypeface(Typeface.create("serif", Typeface.BOLD));
        t.setLineSpacing(0, 1.15f);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        p.setMargins(0, 0, 0, dp(8));
        t.setLayoutParams(p);
        return t;
    }

    private TextView subtitle(String s, int size) {
        TextView t = bodyText(s, size);
        t.setTextColor(GOLD);
        t.setGravity(Gravity.CENTER);
        return t;
    }

    private TextView bodyText(String s, int size) {
        TextView t = new TextView(this);
        t.setText(s);
        t.setTextColor(PALE);
        t.setTextSize(size);
        t.setTypeface(Typeface.create("serif", Typeface.NORMAL));
        t.setLineSpacing(0, 1.22f);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        p.setMargins(0, dp(4), 0, dp(8));
        t.setLayoutParams(p);
        return t;
    }

    private TextView smallLabel(String s) {
        TextView t = new TextView(this);
        t.setText(s.toUpperCase(Locale.ROOT));
        t.setTextColor(GOLD);
        t.setTextSize(12);
        t.setTypeface(Typeface.DEFAULT_BOLD);
        t.setLetterSpacing(0.12f);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        p.setMargins(0, 0, 0, dp(6));
        t.setLayoutParams(p);
        return t;
    }

    private Button goldButton(String text) {
        Button b = new Button(this);
        b.setText(text);
        b.setTextColor(CREAM);
        b.setTextSize(15);
        b.setAllCaps(false);
        b.setTypeface(Typeface.create("serif", Typeface.BOLD));
        GradientDrawable bg = new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, new int[]{Color.rgb(126, 84, 28), Color.rgb(74, 45, 15)});
        bg.setCornerRadius(dp(18));
        bg.setStroke(dp(1), GOLD);
        b.setBackground(bg);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(54));
        p.setMargins(0, dp(8), 0, dp(8));
        b.setLayoutParams(p);
        return b;
    }

    private Button ghostButton(String text) {
        Button b = new Button(this);
        b.setText(text);
        b.setTextColor(GOLD);
        b.setTextSize(14);
        b.setAllCaps(false);
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.argb(130, 4, 20, 45));
        bg.setCornerRadius(dp(16));
        bg.setStroke(dp(1), Color.argb(180, 216, 182, 108));
        b.setBackground(bg);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(50));
        p.setMargins(0, dp(5), 0, dp(5));
        b.setLayoutParams(p);
        return b;
    }

    private EditText input(String hint, int inputType) {
        EditText e = new EditText(this);
        e.setHint(hint);
        e.setHintTextColor(Color.rgb(128, 154, 190));
        e.setTextColor(CREAM);
        e.setTextSize(16);
        e.setSingleLine(true);
        e.setInputType(inputType);
        e.setPadding(dp(16), 0, dp(16), 0);
        e.setBackground(inputBackground());
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dp(54));
        p.setMargins(0, dp(6), 0, dp(8));
        e.setLayoutParams(p);
        return e;
    }

    private GradientDrawable inputBackground() {
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(Color.argb(210, 7, 31, 68));
        bg.setCornerRadius(dp(15));
        bg.setStroke(dp(1), Color.argb(170, 216, 182, 108));
        return bg;
    }

    private LinearLayout card() {
        LinearLayout c = new LinearLayout(this);
        c.setOrientation(LinearLayout.VERTICAL);
        c.setPadding(dp(18), dp(18), dp(18), dp(18));
        GradientDrawable bg = new GradientDrawable();
        bg.setColor(CARD);
        bg.setCornerRadius(dp(20));
        bg.setStroke(dp(1), Color.argb(150, 216, 182, 108));
        c.setBackground(bg);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        p.setMargins(0, dp(7), 0, dp(7));
        c.setLayoutParams(p);
        return c;
    }

    private LinearLayout menuCard(String name, String description, String buttonText, View.OnClickListener listener) {
        LinearLayout c = card();
        c.addView(heading(name, 21));
        c.addView(bodyText(description, 15));
        Button b = goldButton(buttonText);
        b.setOnClickListener(listener);
        c.addView(b);
        return c;
    }

    private View archiveRow(String kind, String text, View.OnClickListener listener) {
        LinearLayout c = card();
        c.addView(smallLabel(kind));
        c.addView(bodyText(text, 15));
        c.setOnClickListener(listener);
        return c;
    }

    private Button backHomeButton() {
        Button b = ghostButton("← Главная палата");
        b.setOnClickListener(v -> showPalace());
        return b;
    }

    private TextView parchmentLine(String label, String value) {
        TextView t = new TextView(this);
        t.setText(label + ":  " + value);
        t.setTextColor(Color.rgb(74, 49, 20));
        t.setTextSize(16);
        t.setTypeface(Typeface.create("serif", Typeface.NORMAL));
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        p.setMargins(0, dp(12), 0, 0);
        t.setLayoutParams(p);
        return t;
    }

    private View lotusMark(int sizeDp) {
        LotusView v = new LotusView(this);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(dp(sizeDp), dp(sizeDp));
        p.gravity = Gravity.CENTER_HORIZONTAL;
        v.setLayoutParams(p);
        return v;
    }

    private Space space(int h) {
        Space s = new Space(this);
        s.setLayoutParams(new LinearLayout.LayoutParams(1, dp(h)));
        return s;
    }

    private String emptyDash(String s) {
        return s == null || s.trim().length() == 0 ? "—" : s;
    }

    private int dp(int n) {
        return (int) (n * getResources().getDisplayMetrics().density + 0.5f);
    }

    @Override
    public void onBackPressed() {
        if ("intro".equals(currentScreen)) {
            super.onBackPressed();
        } else if ("palace".equals(currentScreen)) {
            showIntro();
        } else if ("reader".equals(currentScreen)) {
            showReadingHall();
        } else if ("registered".equals(currentScreen)) {
            showRegistration();
        } else {
            showPalace();
        }
    }

    private static class BookItem {
        final String label;
        final String title;
        final String note;
        BookItem(String label, String title, String note) {
            this.label = label;
            this.title = title;
            this.note = note;
        }
    }

    private static class PersonItem {
        final String name;
        final String role;
        PersonItem(String name, String role) {
            this.name = name;
            this.role = role;
        }
    }

    private class LotusView extends View {
        private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        LotusView(Context c) { super(c); }
        @Override protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            float w = getWidth();
            float h = getHeight();
            float cx = w / 2f;
            float cy = h * 0.56f;
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(Math.max(2f, w * 0.025f));
            paint.setColor(GOLD);
            drawPetal(canvas, cx, cy, w * .16f, h * .43f, 0f);
            drawPetal(canvas, cx, cy, w * .16f, h * .36f, -33f);
            drawPetal(canvas, cx, cy, w * .16f, h * .36f, 33f);
            drawPetal(canvas, cx, cy + h * .05f, w * .16f, h * .30f, -62f);
            drawPetal(canvas, cx, cy + h * .05f, w * .16f, h * .30f, 62f);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.argb(90, 216, 182, 108));
            canvas.drawCircle(cx, cy + h * .13f, w * .07f, paint);
        }
        private void drawPetal(Canvas canvas, float cx, float cy, float halfW, float height, float angle) {
            canvas.save();
            canvas.rotate(angle, cx, cy);
            Path p = new Path();
            p.moveTo(cx, cy);
            p.cubicTo(cx - halfW, cy - height * .25f, cx - halfW, cy - height * .72f, cx, cy - height);
            p.cubicTo(cx + halfW, cy - height * .72f, cx + halfW, cy - height * .25f, cx, cy);
            canvas.drawPath(p, paint);
            canvas.restore();
        }
    }

    private class StarFieldView extends View {
        private final Paint bg = new Paint();
        private final Paint star = new Paint(Paint.ANTI_ALIAS_FLAG);
        private float[] sx = new float[0];
        private float[] sy = new float[0];
        private float[] sr = new float[0];
        StarFieldView(Context context) { super(context); }
        @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
            super.onSizeChanged(w, h, oldw, oldh);
            int count = 110;
            sx = new float[count];
            sy = new float[count];
            sr = new float[count];
            Random r = new Random(7777777L);
            for (int i = 0; i < count; i++) {
                sx[i] = r.nextFloat() * w;
                sy[i] = r.nextFloat() * h;
                sr[i] = 0.6f + r.nextFloat() * 2.2f;
            }
        }
        @Override protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            bg.setShader(new LinearGradient(0, 0, 0, getHeight(), new int[]{NAVY, NAVY_2, Color.rgb(3, 18, 43), NAVY}, null, Shader.TileMode.CLAMP));
            canvas.drawRect(0, 0, getWidth(), getHeight(), bg);
            bg.setShader(null);
            for (int i = 0; i < sx.length; i++) {
                int alpha = 90 + (i % 5) * 28;
                star.setColor(Color.argb(Math.min(230, alpha), i % 7 == 0 ? 216 : 174, i % 7 == 0 ? 182 : 211, 255));
                canvas.drawCircle(sx[i], sy[i], sr[i], star);
            }
            Paint glow = new Paint(Paint.ANTI_ALIAS_FLAG);
            glow.setColor(Color.argb(22, 40, 120, 255));
            canvas.drawCircle(getWidth() * .5f, getHeight() * .17f, getWidth() * .46f, glow);
        }
    }
}
