
package com.davidik.dossier;

import android.app.*;
import android.os.*;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {

    int blue = 0xFF246BCE;
    int dark = 0xFF1E2D42;
    int gray = 0xFF718096;
    int light = 0xFFF2F6FC;

    LinearLayout root;
    EditText fullName, birthDate, country, city, profession, details;

    GradientDrawable shape(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        return d;
    }

    TextView label(String value, int size, int color) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        return t;
    }

    void space(LinearLayout layout, int height) {
        View v = new View(this);
        layout.addView(v, new LinearLayout.LayoutParams(1, height));
    }

    Button makeButton(String value) {
        Button b = new Button(this);
        b.setText(value);
        b.setTextColor(Color.WHITE);
        b.setTextSize(15);
        b.setAllCaps(false);
        b.setBackground(shape(blue, 20));
        return b;
    }

    EditText makeField(String hint) {
        EditText e = new EditText(this);
        e.setHint(hint);
        e.setTextSize(15);
        e.setSingleLine(true);
        e.setPadding(22, 14, 22, 14);
        e.setBackground(shape(light, 16));
        return e;
    }

    void setup(String title) {
        root = new LinearLayout(this);
        root.setOrientation(1);
        root.setPadding(24, 25, 24, 25);
        root.setBackgroundColor(Color.WHITE);

        ScrollView scroll = new ScrollView(this);
        scroll.addView(root);
        setContentView(scroll);

        TextView logo = label("DaviDossier", 25, blue);
        logo.setTypeface(null, Typeface.BOLD);
        root.addView(logo);

        space(root, 8);
        root.addView(label(title, 14, gray));
    }

    void addField(String title, EditText field) {
        space(root, 15);
        TextView t = label(title, 14, dark);
        t.setTypeface(null, Typeface.BOLD);
        root.addView(t);
        space(root, 7);
        root.addView(field);
    }

    @Override
    public void onCreate(Bundle b) {
        super.onCreate(b);

        getWindow().setStatusBarColor(Color.WHITE);
        getWindow().setNavigationBarColor(Color.WHITE);
        getWindow().getDecorView().setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR |
            View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        );

        showHome();
    }

    void showHome() {
        setup("Информационно-аналитическая система");

        space(root, 45);

        TextView title = label("Добро пожаловать!", 27, dark);
        title.setTypeface(null, Typeface.BOLD);
        root.addView(title);

        space(root, 12);

        root.addView(label(
            "Создавайте структурированные информационные " +
            "справки в официально-деловом стиле.",
            15, gray
        ));

        space(root, 35);

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(1);
        card.setPadding(22, 25, 22, 25);
        card.setBackground(shape(0xFFEAF2FF, 22));

        card.addView(label("НОВЫЙ ДОКУМЕНТ", 12, blue));
        space(card, 15);

        TextView cardTitle = label(
            "Информационная справка", 21, dark
        );
        cardTitle.setTypeface(null, Typeface.BOLD);
        card.addView(cardTitle);

        space(card, 12);

        card.addView(label(
            "Заполните сведения и сформируйте документ.",
            14, gray
        ));

        space(card, 22);

        Button create = makeButton("Создать документ");
        card.addView(create);
        root.addView(card);

        create.setOnClickListener(v -> showForm());

        space(root, 35);

        TextView features = label("ВОЗМОЖНОСТИ", 13, blue);
        features.setTypeface(null, Typeface.BOLD);
        root.addView(features);

        space(root, 17);
        root.addView(label("📄  Формирование справок", 16, dark));
        space(root, 17);
        root.addView(label("✏️  Редактирование документов", 16, dark));
        space(root, 17);
        root.addView(label("🤖  Подготовка текста с ИИ", 16, dark));
        space(root, 17);
        root.addView(label("📥  Экспорт документов", 16, dark));

        space(root, 45);
        root.addView(label("DaviDossier • Version 1.0", 12, gray));
    }

    void showForm() {
        setup("Создание нового документа");

        space(root, 25);

        TextView title = label("Сведения", 25, dark);
        title.setTypeface(null, Typeface.BOLD);
        root.addView(title);

        space(root, 8);
        root.addView(label(
            "Введите только сведения, которые имеете право использовать.",
            13, gray
        ));

        fullName = makeField("Фамилия, имя, отчество");
        birthDate = makeField("ДД.ММ.ГГГГ");
        country = makeField("Страна");
        city = makeField("Город");
        profession = makeField("Профессия");

        addField("ФИО *", fullName);
        addField("Дата рождения", birthDate);
        addField("Страна", country);
        addField("Город", city);
        addField("Профессия", profession);

        space(root, 20);
        root.addView(label("Дополнительная информация", 14, dark));
        space(root, 8);

        details = new EditText(this);
        details.setHint("Введите дополнительные сведения...");
        details.setTextSize(15);
        details.setGravity(Gravity.TOP);
        details.setMinLines(5);
        details.setPadding(20, 18, 20, 18);
        details.setBackground(shape(light, 16));
        root.addView(details);

        space(root, 25);

        Button preview = makeButton("Предварительный просмотр");
        root.addView(preview);

        preview.setOnClickListener(v -> {
            if (fullName.getText().toString().trim().isEmpty()) {
                fullName.setError("Введите ФИО");
                return;
            }
            showPreview();
        });

        space(root, 15);

        Button back = makeButton("На главную");
        root.addView(back);
        back.setOnClickListener(v -> showHome());
    }

    void showPreview() {
        setup("Предварительный просмотр");

        space(root, 25);

        TextView heading = label(
            "ИНФОРМАЦИОННАЯ СПРАВКА",
            20, blue
        );
        heading.setTypeface(null, Typeface.BOLD);
        heading.setGravity(Gravity.CENTER);
        root.addView(heading);

        space(root, 8);

        TextView number = label(
            "ДОКУМЕНТ № 000001", 12, gray
        );
        number.setGravity(Gravity.CENTER);
        root.addView(number);

        space(root, 25);

        LinearLayout document = new LinearLayout(this);
        document.setOrientation(1);
        document.setPadding(22, 22, 22, 22);
        document.setBackground(shape(light, 18));

        addLine(document, "Фамилия, имя, отчество",
            fullName.getText().toString());

        addLine(document, "Дата рождения",
            birthDate.getText().toString());

        addLine(document, "Страна",
            country.getText().toString());

        addLine(document, "Город",
            city.getText().toString());

        addLine(document, "Профессия",
            profession.getText().toString());

        addLine(document, "Дополнительные сведения",
            details.getText().toString());

        space(document, 25);

        document.addView(label(
            "Сведения представлены пользователем. " +
            "Независимая проверка достоверности не проводилась. " +
            "Документ не является официальным заключением.",
            12, gray
        ));

        root.addView(document);

        space(root, 25);

        Button edit = makeButton("Редактировать");
        root.addView(edit);
        edit.setOnClickListener(v -> showForm());

        space(root, 12);

        Button home = makeButton("На главную");
        root.addView(home);
        home.setOnClickListener(v -> showHome());
    }

    void addLine(LinearLayout layout, String title, String value) {
        space(layout, 12);

        TextView name = label(title, 12, gray);
        layout.addView(name);

        space(layout, 5);

        TextView content = label(
            value.trim().isEmpty() ? "Не указано" : value,
            15, dark
        );
        layout.addView(content);
    }
}
