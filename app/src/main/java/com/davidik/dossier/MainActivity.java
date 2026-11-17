package com.davidik.dossier;

import android.app.*;
import android.os.*;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity {

    LinearLayout root;

    int blue = Color.rgb(30, 100, 200);
    int dark = Color.rgb(30, 45, 65);
    int gray = Color.rgb(105, 120, 140);

    GradientDrawable bg(int color, int radius) {
        GradientDrawable d = new GradientDrawable();
        d.setColor(color);
        d.setCornerRadius(radius);
        return d;
    }

    TextView text(String value, int size, int color) {
        TextView t = new TextView(this);
        t.setText(value);
        t.setTextSize(size);
        t.setTextColor(color);
        return t;
    }

    EditText field(String hint) {
        EditText e = new EditText(this);
        e.setHint(hint);
        e.setTextSize(15);
        e.setSingleLine(true);
        e.setPadding(35, 20, 35, 20);
        e.setBackground(bg(Color.rgb(245, 248, 252), 20));
        return e;
    }

    void space(LinearLayout layout, int height) {
        View v = new View(this);
        layout.addView(v, new LinearLayout.LayoutParams(
            1, height
        ));
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
        root = new LinearLayout(this);
        root.setOrientation(1);
        root.setBackgroundColor(Color.WHITE);
        root.setPadding(25, 30, 25, 20);

        ScrollView scroll = new ScrollView(this);
        scroll.addView(root);

        setContentView(scroll);

        TextView logo = text("DaviDossier", 27, blue);
        logo.setTypeface(null, Typeface.BOLD);
        root.addView(logo);

        space(root, 8);

        root.addView(text(
            "Система формирования информационных справок",
            14, gray
        ));

        space(root, 35);

        TextView title = text("Добро пожаловать", 25, dark);
        title.setTypeface(null, Typeface.BOLD);
        root.addView(title);

        space(root, 10);

        root.addView(text(
            "Создавайте структурированные информационные документы " +
            "на основе предоставленных сведений.",
            15, gray
        ));

        space(root, 30);

        LinearLayout card = new LinearLayout(this);
        card.setOrientation(1);
        card.setPadding(25, 25, 25, 25);
        card.setBackground(bg(Color.rgb(241, 247, 255), 25));

        card.addView(text("НОВЫЙ ДОКУМЕНТ", 12, blue));

        space(card, 12);

        TextView cardTitle = text(
            "Создать информационную справку",
            21, dark
        );
        cardTitle.setTypeface(null, Typeface.BOLD);
        card.addView(cardTitle);

        space(card, 10);

        card.addView(text(
            "Заполните сведения и получите документ " +
            "в официально-деловом стиле.",
            14, gray
        ));

        space(card, 20);

        Button create = new Button(this);
        create.setText("Создать досье");
        create.setTextColor(Color.WHITE);
        create.setAllCaps(false);
        create.setBackground(bg(blue, 18));
        card.addView(create);

        create.setOnClickListener(v -> showForm());

        root.addView(card);

        space(root, 30);

        root.addView(text("ВОЗМОЖНОСТИ", 13, blue));

        space(root, 15);

        root.addView(text("📄  Генерация документов", 17, dark));
        space(root, 15);
        root.addView(text("✏️  Редактирование текста", 17, dark));
        space(root, 15);
        root.addView(text("📚  История документов", 17, dark));
        space(root, 15);
        root.addView(text("📥  Экспорт в PDF", 17, dark));

        space(root, 35);

        root.addView(text(
            "DaviDossier • Version 1.0",
            12, gray
        ));
    }

    void showForm() {
        Toast.makeText(
            this,
            "Форма будет добавлена следующим этапом",
            Toast.LENGTH_SHORT
        ).show();
    }
}
