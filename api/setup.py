"""
Visita /api/setup una sola vez después de hacer deploy para
registrar el webhook de Telegram con la URL de Vercel.
"""
import os
import json
import requests
from http.server import BaseHTTPRequestHandler
from urllib.parse import urlparse, parse_qs

TOKEN = "8675711337:AAF7Zy-qqSuK9Yhokgr34zPeNOnMPI2v6G4"


class handler(BaseHTTPRequestHandler):
    def log_message(self, format, *args):
        pass

    def do_GET(self):
        # Vercel inyecta VERCEL_URL automáticamente (sin https://)
        vercel_url = os.environ.get("VERCEL_URL", "")

        # Permite pasar URL custom: /api/setup?url=mi-dominio.com
        qs = parse_qs(urlparse(self.path).query)
        custom_url = qs.get("url", [None])[0]

        base_url = custom_url or vercel_url

        if not base_url:
            self._respond(500, {"ok": False, "error": "No se encontró VERCEL_URL. Pasa ?url=tu-dominio.vercel.app"})
            return

        webhook_url = f"https://{base_url}/api/webhook"

        r = requests.post(
            f"https://api.telegram.org/bot{TOKEN}/setWebhook",
            json={"url": webhook_url, "drop_pending_updates": True},
            timeout=10,
        )
        result = r.json()
        result["webhook_url_set"] = webhook_url

        self._respond(200 if result.get("ok") else 500, result)

    def _respond(self, status: int, data: dict):
        body = json.dumps(data, indent=2, ensure_ascii=False).encode("utf-8")
        self.send_response(status)
        self.send_header("Content-Type", "application/json; charset=utf-8")
        self.end_headers()
        self.wfile.write(body)
