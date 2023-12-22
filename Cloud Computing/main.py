from flask import Flask, request, jsonify
import tensorflow as tf
import numpy as np
from tensorflow.keras.preprocessing.text import Tokenizer
from tensorflow.keras.preprocessing.sequence import pad_sequences

app = Flask(__name__)
port = 3000

model = None

def load_model():
    global model
    model = tf.keras.models.load_model('./hoax_detection_model3.h5')

def validate(text):
    try:
        if not model:
            load_model()

        
        tokenizer = Tokenizer()
        tokenizer.fit_on_texts([text])
        sequence = tokenizer.texts_to_sequences([text])


        max_length = 1000  
        padded_sequence = pad_sequences(sequence, maxlen=max_length)

        
        prediction = model.predict(padded_sequence)
        label = np.argmax(prediction)
        message = "Berita tidak terdeteksi sebagai hoax" if label == 0 else "Berita terdeteksi sebagai Hoax"

        return {
            "validate": str(label),
            "text": text,
            "message": message,
            "Label": int(label),
        }
    except tf.errors.NotFoundError as err:
        return {"error": str(err)}, 404
    except Exception as err:
        print(err)
        return {"error": str(err)}, 500

@app.route('/')
def index():
    return 'Server active! By Flask'

@app.route('/validate', methods=['POST'])
def validate_endpoint():
    text = request.json.get('text')

    if not text:
        return jsonify({"error": "URL tidak boleh kosong"}), 400

    data = validate(text)
    return jsonify({"data": data}), 200

if __name__ == '__main__':
    load_model()  
    app.run(host='0.0.0.0', port=8080, debug=True)