import logging
import requests
from telegram import Update, InlineKeyboardButton, InlineKeyboardMarkup
from telegram.ext import Application, CommandHandler, CallbackQueryHandler, ContextTypes

TOKEN = "8675711337:AAF7Zy-qqSuK9Yhokgr34zPeNOnMPI2v6G4"

logging.basicConfig(
    format="%(asctime)s - %(name)s - %(levelname)s - %(message)s",
    level=logging.INFO
)

CAT_MOODS = {
    "normal": ("🐱", "¡Toma tu gatito del día!"),
    "sleepy": ("😴", "Este gatito quiere dormir todo el día..."),
    "wild":   ("🐯", "¡MODO GATITO SALVAJE ACTIVADO!"),
    "love":   ("😻", "¡Este gatito te manda todo su amor!"),
    "fancy":  ("🎩", "Un gatito de alta sociedad para ti."),
}


def get_random_cat_url():
    try:
        r = requests.get(
            "https://api.thecatapi.com/v1/images/search",
            params={"mime_types": "jpg,png", "size": "med"},
            timeout=8,
        )
        r.raise_for_status()
        return r.json()[0]["url"]
    except Exception:
        # fallback: cataas devuelve imagen directa
        return "https://cataas.com/cat"


def get_cat_fact():
    try:
        r = requests.get("https://catfact.ninja/fact", timeout=8)
        r.raise_for_status()
        return r.json()["fact"]
    except Exception:
        return "Los gatos pasan el 70% de su vida durmiendo. ¡Sabios ellos!"


def build_keyboard():
    return InlineKeyboardMarkup([
        [
            InlineKeyboardButton("🐱 Gatito normal",  callback_data="cat:normal"),
            InlineKeyboardButton("😴 Gatito dormilón", callback_data="cat:sleepy"),
        ],
        [
            InlineKeyboardButton("🐯 Gatito salvaje", callback_data="cat:wild"),
            InlineKeyboardButton("😻 Gatito amoroso", callback_data="cat:love"),
        ],
        [
            InlineKeyboardButton("🎩 Gatito elegante", callback_data="cat:fancy"),
        ],
        [
            InlineKeyboardButton("💡 Dato gatuno aleatorio", callback_data="fact"),
        ],
    ])


async def start(update: Update, context: ContextTypes.DEFAULT_TYPE):
    await update.message.reply_text(
        "😺 *¡Bienvenido al Bot Gatuno Supremo!* 😺\n\n"
        "Presiona cualquier botón para recibir gatitos según tu estado de ánimo 🐾\n"
        "O pide un dato felino para impresionar a tus amigos 🧠",
        parse_mode="Markdown",
        reply_markup=build_keyboard(),
    )


async def button_handler(update: Update, context: ContextTypes.DEFAULT_TYPE):
    query = update.callback_query
    await query.answer("¡Consiguiendo tu gatito... 🐾")

    data = query.data

    if data.startswith("cat:"):
        mood = data.split(":")[1]
        emoji, caption = CAT_MOODS.get(mood, CAT_MOODS["normal"])
        cat_url = get_random_cat_url()
        await query.message.reply_photo(
            photo=cat_url,
            caption=f"{emoji} *{caption}*",
            parse_mode="Markdown",
            reply_markup=build_keyboard(),
        )

    elif data == "fact":
        fact = get_cat_fact()
        await query.message.reply_text(
            f"🧠 *Dato gatuno del día:*\n\n_{fact}_",
            parse_mode="Markdown",
            reply_markup=build_keyboard(),
        )


async def error_handler(update: object, context: ContextTypes.DEFAULT_TYPE):
    logging.error("Error:", exc_info=context.error)


def main():
    app = Application.builder().token(TOKEN).build()
    app.add_handler(CommandHandler("start", start))
    app.add_handler(CallbackQueryHandler(button_handler))
    app.add_error_handler(error_handler)

    print("🐱 ¡Bot Gatuno Supremo iniciado! Esperando usuarios...")
    app.run_polling(allowed_updates=Update.ALL_TYPES)


if __name__ == "__main__":
    main()
